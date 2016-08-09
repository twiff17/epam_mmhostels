package by.epam.hostelbeta.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.dto.HostelDTO;
import by.epam.hostelbeta.entity.Hostel;

public class HostelDAO extends AbstractDAO {
	private static final String SELECT_POPULAR_HOSTELS = "SELECT SQL_CALC_FOUND_ROWS * FROM `hostel` LIMIT ?, ?";
	private static final String SELECT_ALL_HOSTELS = "SELECT SQL_CALC_FOUND_ROWS * FROM `v_hostel_information` LIMIT ?, ?";
	
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
	
	private int noOfRecords;
	
	public List<Hostel> findPopularHostels(int offset, 
            int noOfRecords) throws DAOException{
		ArrayList<Hostel> hostels = new ArrayList<Hostel>();
		try(PreparedStatement st = connection.prepareStatement(SELECT_POPULAR_HOSTELS)){
			st.setInt(1, offset);
			st.setInt(2, noOfRecords);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Hostel hostel = new Hostel();
				fillHostel(rs, hostel);
				hostels.add(hostel);
			}
			rs = st.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
		}catch(SQLException e){
			throw new DAOException(e);
		}
		return hostels;
	}
	
	
	
	public List<HostelDTO> findAllHostels(int offset, 
            int noOfRecords) throws DAOException{
		ArrayList<HostelDTO> hostels = new ArrayList<HostelDTO>();
		try(PreparedStatement st = connection.prepareStatement(SELECT_ALL_HOSTELS)){
			st.setInt(1, offset);
			st.setInt(2, noOfRecords);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				HostelDTO hostel = new HostelDTO();
				fillHostelDto(rs, hostel);
				hostels.add(hostel);
			}
			rs = st.executeQuery("SELECT FOUND_ROWS()");
            if(rs.next())
                this.noOfRecords = rs.getInt(1);
		}catch(SQLException e){
			throw new DAOException(e);
		}
		return hostels;
		
	}

	private void fillHostel(ResultSet rs, Hostel hostel) throws SQLException{
		hostel.setCountry(rs.getString(COUNTRY));
		hostel.setCity(rs.getString(CITY));
		hostel.setName(rs.getString(NAME));
		hostel.setDescription(rs.getString(DESCRIPTION));
	}
	
	private void fillHostelDto(ResultSet rs, HostelDTO hostelDto) throws SQLException{
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
