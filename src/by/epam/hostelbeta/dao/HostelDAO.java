package by.epam.hostelbeta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.entity.Hostel;

public class HostelDAO extends AbstractDAO {
	private static final String SELECT_ALL_HOSTELS = "SELECT SQL_CALC_FOUND_ROWS * FROM `hostel` LIMIT ";
	
//	private static final String HOSTEL_ID = "HostelId";
	private static final String NAME = "Name";
	private static final String COUNTRY = "Country";
	private static final String CITY = "City";
//	private static final String ADDRESS = "Address";
//	private static final String CURRENCY = "Currency";
//	private static final String STANDART_PRICE = "StandartPrice";
//	private static final String PHONE = "Phone";
	private static final String DESCRIPTION = "Description";
	
	private int noOfRecords;
	
	public List<Hostel> findAllHostels(int offset, 
            int noOfRecords) throws DAOException{
		ArrayList<Hostel> hostels = new ArrayList<Hostel>();
		try(Statement st = connection.createStatement()){
			ResultSet rs = st.executeQuery(SELECT_ALL_HOSTELS + offset + ", " + noOfRecords);
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
	
	public int getNoOfRecords() {
		return noOfRecords;
	}

	private void fillHostel(ResultSet rs, Hostel hostel) throws SQLException{
		hostel.setCountry(rs.getString(COUNTRY));
		hostel.setCity(rs.getString(CITY));
		hostel.setName(rs.getString(NAME));
		hostel.setDescription(rs.getString(DESCRIPTION));
	}
}
