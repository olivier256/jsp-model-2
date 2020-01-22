package proxy;

import java.util.Comparator;
import java.util.Map;

public interface ComparatorsList<T> {
	Map<String, Comparator<T>> comparatorsList();

}
