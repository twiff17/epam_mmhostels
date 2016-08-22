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
import by.epam.hostelbeta.pool.ConnectionDecorator;

public class HostelDAO implements IHostelDAO {
	private static final String SELECT_POPULAR_HOSTELS = "SELECT * from `hostel` join `order` on `hostel`.HostelId = `order`.HostelId GROUP BY `hostel`.`HostelId` ORDER BY COUNT(`order`.`OrderId`) DESC LIMIT 5";
	private static final String SELECT_ALL_HOSTELS_BY_PAGES = "SELECT SQL_CALC_FOUND_ROWS * FROM `v_hostel_information` LIMIT ?, ?";
	private static final String SELECT_ALL_HOSTELS = "SELECT * FROM `hostel`";
	private static final String SELECT_HOSTEL_BY_ID = "SELECT * FROM `hostel` WHERE `HostelId` = ?";
	private static final String DELETE_HOSTEL = "DELETE FROM `hostel` WHERE `HostelId` = ?";
	private static final String ADD_HOSTEL = "INSERT INTO `hostel`(`Name`, `Country`, `City`, `Address`, `Currency`, `StandartPrice`, `Phone`, `Description`, `ImageName`) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String EDIT_HOSTEL = "UPDATE `hostel` SET `Name` = ?, `Country` = ?, `City` = ?, `Address` = ?, `Currency` = ?, `StandartPrice` = ?, `Phone` = ?, `Description` = ? WHERE `HostelId` = ?";
	private static final String SELECT_HOSTELS_BY_COUNTRY = "SELECT * FROM `v_hostel_information` WHERE `Country` LIKE ?";

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
	private static final String IMAGE_NAME = "ImageName";

	private int noOfRecords;

	public List<Hostel> findPopularHostels() throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
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
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
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
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
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

	public void deleteHostel(long hostelId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(DELETE_HOSTEL)) {
			ps.setLong(1, hostelId);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public void addHostel(Hostel hostel) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(ADD_HOSTEL)) {
			ps.setString(1, hostel.getName());
			ps.setString(2, hostel.getCountry());
			ps.setString(3, hostel.getCity());
			ps.setString(4, hostel.getAddress());
			ps.setString(5, hostel.getCurrency());
			ps.setInt(6, hostel.getStandartPrice());
			ps.setString(7, hostel.getPhone());
			ps.setString(8, hostel.getDescription());
			ps.setString(9, hostel.getImageName());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public Hostel findHostelById(long hostelId) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		Hostel hostel = null;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_HOSTEL_BY_ID)) {
			ps.setLong(1, hostelId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				hostel = new Hostel();
				fillHostel(rs, hostel);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}

		return hostel;
	}

	public void editHostel(Hostel hostel) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		try (PreparedStatement ps = connection.prepareStatement(EDIT_HOSTEL)) {
			ps.setString(1, hostel.getName());
			ps.setString(2, hostel.getCountry());
			ps.setString(3, hostel.getCity());
			ps.setString(4, hostel.getAddress());
			ps.setString(5, hostel.getCurrency());
			ps.setInt(6, hostel.getStandartPrice());
			ps.setString(7, hostel.getPhone());
			ps.setString(8, hostel.getDescription());

			ps.setLong(9, hostel.getHostelId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			connection.close();
		}
	}

	public List<HostelDTO> findByCountry(String country) throws DAOException {
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		ArrayList<HostelDTO> hostels = new ArrayList<HostelDTO>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_HOSTELS_BY_COUNTRY)) {
			ps.setString(1, "%" + country + "%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				HostelDTO hostel = new HostelDTO();
				fillHostelDto(rs, hostel);
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
		hostel.setImageName(rs.getString(IMAGE_NAME));
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
		hostelDto.setImageName(rs.getString(IMAGE_NAME));
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
