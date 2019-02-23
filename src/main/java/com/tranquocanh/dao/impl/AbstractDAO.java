package com.tranquocanh.dao.impl;

import java.sql.Timestamp;
import java.sql.Types;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tranquocanh.dao.GenericDAO;
import com.tranquocanh.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/newservlet12month2018";
			String user = "root";
			String password = "111996";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... params) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			// set parameters
			setParameters(statement, params);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				return null;
			}

		}

	}

	private void setParameters(PreparedStatement statement, Object... params) {
		try {
			for (int i = 0; i < params.length; i++) {
				Object param = params[i];
				int index = i + 1;
				if (param instanceof Long) {
					statement.setLong(index, (Long) param);
				}
				if (param instanceof String) {
					statement.setString(index, (String) param);
				}
				if(param instanceof Integer) {
					statement.setInt(index,(Integer) param);
				}
				if(param instanceof Float) {
					statement.setFloat(index,(Float) param);
				}
				if(param instanceof Timestamp) {
					statement.setTimestamp(index,(Timestamp) param);
				} /*
					 * else if(param == null) { statement.setNull(index, Types.NULL); }
					 */
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(String sql, Object... params) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParameters(statement, params);
			statement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			if(connection != null) {
				try {
					System.out.print(e.getMessage());
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
	}

	@Override
	public Long insert(String sql, Object... params) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Long id = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameters(statement, params);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				return null;
			}

		}
		
	}

	@Override
	public Integer getTotalItem(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
            Integer count = 0;
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				count = resultSet.getInt(1); 
			}
			return count;
		} catch (SQLException e) {
			return 0;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (SQLException e) {
				return 0;
			}

		}
	}
}
