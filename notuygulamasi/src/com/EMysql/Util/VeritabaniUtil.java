package com.EMysql.Util;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class VeritabaniUtil {
	static Connection conn=null;
	public static Connection Baglan() {
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost/proje", "root", "");
			return conn;
		}
		catch (Exception e) {
			System.out.println(e.getMessage().toString());
			return null;
			}
	
	}
	
	public static String MD5sifrele(String icerik) {
		
		try {
			MessageDigest md=MessageDigest().getInstance("MD5");
			//byte olarak okuma
			byte[] sifreleme=md.digest(icerik.getBytes());
			BigInteger no=new BigInteger(1,sifreleme);
			String hashicerik=no.toString(16);
			while(hashicerik.length()<32) {
				hashicerik="0"+hashicerik;
			}
			return hashicerik;
			
		}
		catch(NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static MessageDigest MessageDigest() {
		// TODO Auto-generated method stub
		return null;
	}

}
