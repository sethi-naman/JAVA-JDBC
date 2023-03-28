import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Operartion {
	
	Connection conn= null;
	Statement stm= null;
	PreparedStatement pstm= null;
	
	
	public Operartion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/records","root","javaexpert");
			stm= conn.createStatement();
			stm.execute("create table if not exists data(name text, email text, password text, mobile text,image text)");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		}
	 int Insert(String n, String e, String p, String m,String fname)throws Exception {
		String q = "Insert into data values(?,?,?,?,?)";
		pstm = conn.prepareStatement(q);
		pstm.setString(1, n);
		pstm.setString(2, e);
		pstm.setString(3, p);
		pstm.setString(4, m);
		pstm.setString(5, fname);
		
		return pstm.executeUpdate(); 
		
		
	}
	public ArrayList<HashMap> read() {
		ArrayList<HashMap> records = new ArrayList<HashMap>();
		try {
			ResultSet data = stm.executeQuery("select * from data");
			while(data.next()) {
				//System.out.println(data.getString(1));
				//System.out.println(data.getString(2));
				//System.out.println(data.getString(3));
				//System.out.println(data.getString(4));
				//System.out.println(data.getString(5));
				HashMap map= new HashMap();
				
				map.put("name", data.getString(1));
				map.put("email", data.getString(2));
				map.put("password", data.getString(3));
				map.put("mobile", data.getString(4));
				map.put("image", data.getString(5));
				records.add(map);
				
				
					
			}
			return records;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<HashMap> readbyemail(String email) {
		// TODO Auto-generated method stub
		ArrayList<HashMap> records = new ArrayList<HashMap>();
		try {
			pstm = conn.prepareStatement("select * from data where email=?");
			pstm.setString(1, email);
			ResultSet data = pstm.executeQuery();
			while(data.next()) {
				//System.out.println(data.getString(1));
				//System.out.println(data.getString(2));
				//System.out.println(data.getString(3));
				//System.out.println(data.getString(4));
				//System.out.println(data.getString(5));
				HashMap map= new HashMap();
				
				map.put("name", data.getString(1));
				map.put("email", data.getString(2));
				map.put("password", data.getString(3));
				map.put("mobile", data.getString(4));
				map.put("image", data.getString(5));
				records.add(map);
				
				
					
			}
			return records;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int Update(String name, String email, String password, String mobile,String fname ) {
		try {
			String q = "update data set name=?,password=?,mobile=?,image=? Where email=?";
			pstm = conn.prepareStatement(q);
			pstm.setString(1, name);
			pstm.setString(2, password);
			pstm.setString(3, mobile);
			pstm.setString(4, fname);
			pstm.setString(5, email);
			
			return pstm.executeUpdate(); 
		
			
		} catch (Exception e) {
			// TODO: handle exception
			return 1;
		}
	}
	  public int Update(String name, String email, String password, String mobile) {
		    try {
		      String q = "update data set name=?,password=?,maobile=?,image=? Where email=?";
		      pstm = conn.prepareStatement(q);
		      pstm.setString(1, name);
		      pstm.setString(2, password);
		      pstm.setString(3, mobile);
		      pstm.setString(4, email);
		      
		      return pstm.executeUpdate(); 
		    
		      
		    } catch (Exception e) {
		      // TODO: handle exception
		      return 1;
		    }
	  }
	  public ArrayList<HashMap> deletebyemail(String email) {
		    try {
		    	pstm=conn.prepareStatement("delete from data where email=?");
		        pstm.setString(1, email);
		        pstm.executeUpdate();
		      } catch (SQLException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
		      // TODO Auto-generated method stub
			return null;
		      
		    }
	    
	  }
	
