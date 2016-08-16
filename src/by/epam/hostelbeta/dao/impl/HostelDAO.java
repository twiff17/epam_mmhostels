package by.epam.hostelbeta.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.IHostelDAO;
import by.epam.hostelbeta.domain.dto.HostelDTO;
import by.epam.hostelbeta.domain.entity.Hostel;
import by.epam.hostelbeta.pool.ConnectionPool;
import by.epam.hostelbeta.pool.ConnectionWrapper;

public class HostelDAO implements IHostelDAO {
	private static final String SELECT_POPULAR_HOSTELS = "SELECT * from `hostel` join `order` on `hostel`.HostelId = `order`.HostelId GROUP BY `hostel`.`HostelId` ORDER BY COUNT(`order`.`OrderId`) DESC LIMIT 5";
	private static final String SELECT_ALL_HOSTELS_BY_PAGES = "SELECT SQL_CALC_FOUND_ROWS * FROM `v_hostel_information` LIMIT ?, ?";
	private static final String SELECT_ALL_HOSTELS = "SELECT * FROM `hostel`";

	private static final String HOSTEL_ID = "HostelId";
	private static final String NAME = "Name";
	private static final String COUNTRY = "Country";
	private static final String CITY = "City";
	private static final String ADDRESS = "Address";
	private static final String CURRENCY = "Currency";
	private static final String PHONE = "Phone";
	private static final String DESCRIPTION = "Description";
	private static final String MIN_PRICE = "MinPrice";
	private static final String MAX_PRICE = "MaxPrice";
	private static final String ROOM_TYPES = "RoomTypes";
	private static final String STANDART_PRICE = "StandartPrice";

	private int noOfRecords;

	public List<Hostel> findPopularHostels() throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		ArrayList<Hostel> hostels = new ArrayList<Hostel>();

		try (PreparedStatement st = connection.prepareStatement(SELECT_POPULAR_HOSTELS)) {
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Hostel hostel = new Hostel();
				fillHostel(rs, hostel);
				hostels.add(hostel);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
		return hostels;
	}

	public List<HostelDTO> findAllHostels(int offset, int noOfRecords) throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		ArrayList<HostelDTO> hostels = new ArrayList<HostelDTO>();

		try (PreparedStatement st = connection.prepareStatement(SELECT_ALL_HOSTELS_BY_PAGES)) {
			st.setInt(1, offset);
			st.setInt(2, noOfRecords);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				HostelDTO hostel = new HostelDTO();
				fillHostelDto(rs, hostel);
				hostels.add(hostel);
			}

			rs = st.executeQuery("SELECT FOUND_ROWS()");
			if (rs.next()) {
				this.noOfRecords = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return hostels;
	}

	public List<Hostel> findAllHostels() throws DAOException {
		ConnectionWrapper connection = ConnectionPool.getInstance().retrieve();
		ArrayList<Hostel> hostels = new ArrayList<Hostel>();

		try (Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(SELECT_ALL_HOSTELS);

			while (rs.next()) {
				Hostel hostel = new Hostel();
				fillHostel(rs, hostel);
				hostels.add(hostel);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return hostels;
	}

	private void fillHostel(ResultSet rs, Hostel hostel) throws SQLException {
		hostel.setHostelId(rs.getLong(HOSTEL_ID));
		hostel.setCountry(rs.getString(COUNTRY));
		hostel.setAddress(rs.getString(ADDRESS));
		hostel.setCurrency(rs.getString(CURRENCY));
		hostel.setPhone(rs.getString(PHONE));
		hostel.setStandartPrice(rs.getInt(STANDART_PRICE));
		hostel.setCity(rs.getString(CITY));
		hostel.setName(rs.getString(NAME));
		hostel.setDescription(rs.getString(DESCRIPTION));
	}

	private void fillHostelDto(ResultSet rs, HostelDTO hostelDto) throws SQLException {
		hostelDto.setHostelId(rs.getLong(HOSTEL_ID));
		hostelDto.setName(rs.getString(NAME));
		hostelDto.setCountry(rs.getString(COUNTRY));
		hostelDto.setCurrency(rs.getString(CURRENCY));
		hostelDto.setCity(rs.getString(CITY));
		hostelDto.setPhone(rs.getString(PHONE));
		hostelDto.setDescription(rs.getString(DESCRIPTION));
		hostelDto.setAddress(rs.getString(ADDRESS));
		hostelDto.setRoomTypes(rs.getString(ROOM_TYPES));
		hostelDto.setMinPrice(rs.getInt(MIN_PRICE));
		hostelDto.setMaxPrice(rs.getInt(MAX_PRICE));
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}
}
