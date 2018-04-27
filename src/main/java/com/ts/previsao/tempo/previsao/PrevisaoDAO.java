package com.ts.previsao.tempo.previsao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.ts.previsao.tempo.database.DataBaseConnectionFactory;

public class PrevisaoDAO {

	private Connection connection;

	public PrevisaoDAO() throws ClassNotFoundException, SQLException {
		this.connection = new DataBaseConnectionFactory().conectar();
	}

	public boolean createTablePrevisao() throws SQLException {
		Statement stmt = this.connection.createStatement();
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("create table if not exists").append("tbprevisao");
		sqlBuilder.append("(");
		sqlBuilder.append("id int not null").append(",");
		sqlBuilder.append("dia date not null").append(",");
		sqlBuilder.append("tempo char(3) not null").append(",");
		sqlBuilder.append("minima float not null").append(",");
		sqlBuilder.append("maxima float not null").append(",");
		sqlBuilder.append("iuv float not null").append(",");
		sqlBuilder.append("primary key (id, dia)").append(",");
		sqlBuilder.append("foreign key (id) references tbcidade(id)");
		sqlBuilder.append(")");
		stmt.executeUpdate(sqlBuilder.toString());
		stmt.close();
		return true;
	}

}
