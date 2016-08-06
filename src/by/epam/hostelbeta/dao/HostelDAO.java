package by.epam.hostelbeta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epam.hostelbeta.entity.Hostel;

public class HostelDAO extends AbstractDAO {
	private static final String SELECT_ALL_HOSTELS = "SELECT * FROM `hostel`";
	
//	private static final String HOSTEL_ID = "HostelId";
	private static final String NAME = "Name";
	private static final String COUNTRY = "Country";
	private static final String CITY = "City";
//	private static final String ADDRESS = "Address";
//	private static final String CURRENCY = "Currency";
//	private static final String STANDART_PRICE = "StandartPrice";
//	private static final String PHONE = "Phone";
	private static final String DESCRIPTION = "Description";
	
	public List<Hostel> findAllHostels() throws DAOException{
		ArrayList<Hostel> hostels = new ArrayList<Hostel>();
		try(Statement st = connection.createStatement()){
			ResultSet rs = st.executeQuery(SELECT_ALL_HOSTELS);
			while(rs.next()){
				Hostel hostel = new Hostel();
				fillHostel(rs, hostel);
				hostels.add(hostel);
			}
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
}
