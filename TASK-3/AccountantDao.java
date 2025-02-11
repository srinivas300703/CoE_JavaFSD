import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountantDao {
    public static boolean addAccountant(Accountant acc)
    {
        try(Connection con = DBConnection.getConnection())
        {
            String sql ="insert into accountant(name,email,phone,password) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, acc.getName());
            ps.setString(2,acc.getEmail());
            ps.setString(3, acc.getPhone());
            ps.setString(4, acc.getPassword());
            return ps.executeUpdate()>0;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Accountant> getAllAccountants()
    {
        List<Accountant> list=new ArrayList<>();
        try(Connection con=DBConnection.getConnection())
        {
            String sql = "select * from accountant";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                list.add(new Accountant(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("password")

                ));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            
        }

        return list;
    }
}
