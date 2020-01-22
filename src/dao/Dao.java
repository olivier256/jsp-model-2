package dao;

import java.util.List;

public interface Dao<KEY, TYPE> {
	List<TYPE> findAll();

}
