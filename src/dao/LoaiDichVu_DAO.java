package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LoaiDichVu;

public class LoaiDichVu_DAO {
	public ArrayList<LoaiDichVu> layThongTin() throws Exception{
		ArrayList<LoaiDichVu> dsLoaiDichVu= new ArrayList<LoaiDichVu>();
		try {
            /*	
            Ket noi SQL
             */
            ConnectDB.getInstance().connect();
            Connection con = ConnectDB.getConnection();
            /*
            Thuc thi cau lenh SQL
             */
            String SQL = "SELECT * FROM LoaiDichVu";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQL);
            while (rs.next()){
                String maLoaiDichVu = rs.getString(1);
                String tenLoaiDichVu = rs.getString(2);
                LoaiDichVu loaiDichVu= new LoaiDichVu(maLoaiDichVu, tenLoaiDichVu);
                dsLoaiDichVu.add(loaiDichVu);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsLoaiDichVu;
	}
	public boolean themLoaiDichVu(LoaiDichVu loaiDichVu)  {
        //thêm dịch vụ
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement statement =null;
        String SQL = "INSERT INTO LoaiDichVu VALUES (?,?,?,?,?)";
        int n = 0;
        try {

            statement = con.prepareStatement(SQL);
            statement.setString(1,loaiDichVu.getMaDichVu());
            statement.setString(2,loaiDichVu.getTenLoaiDichVu());
            n = statement.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
            }finally {
            try {
                statement.close();
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
        }
        return n > 0;
    }
}
