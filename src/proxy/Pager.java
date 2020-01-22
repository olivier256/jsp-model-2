package proxy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public abstract class Pager<T> implements ComparatorsList<T>, ListToArray<T> {
	private static final int WINDOW_SIZE = 7;

	private List<T> list;

	private int windowSize;

	private int fromIndex;

	private int toIndex;

	private String sortColumn;

	private boolean descendingSort;

	private Map<String, Comparator<T>> columnsAndTheirComparator;

	public Pager(final List<T> list) {
		this(list, WINDOW_SIZE);
	}

	public Pager(final List<T> list, final int windowSize) {
		this.list = list;
		this.windowSize = windowSize;
		fromIndex = 0;
		toIndex = windowSize;
		descendingSort = false;
		columnsAndTheirComparator = comparatorsList();
	}

	public List<T> getData() {
		return list.subList(fromIndex, toIndex);
	}

	public void previous() {
		if (fromIndex >= windowSize) {
			toIndex = fromIndex;
			fromIndex -= windowSize;
		}
	}

	public void next() {
		if (toIndex < list.size() - windowSize) {
			fromIndex = toIndex;
			toIndex += windowSize;
		}
	}

	public boolean hasPrevious() {
		return fromIndex >= windowSize;
	}

	public boolean hasNext() {
		return toIndex < list.size() - windowSize;
	}

	public void orderBy(final String column) {
		if (this.sortColumn == null) {
			this.sortColumn = column;
			descendingSort = false;
		} else {
			if (this.sortColumn.equals(column)) {
				descendingSort = !descendingSort;
			} else {
				this.sortColumn = column;
				descendingSort = false;
			}
		}

		fromIndex = 0;
		toIndex = windowSize;

		T[] array = listToArray(list);
		Comparator<T> comparator = columnsAndTheirComparator.get(column);
		if (descendingSort) {
			comparator = reverse(comparator);
		}
		Arrays.sort(array, comparator);
		list = Arrays.asList(array);
	}

	private Comparator<T> reverse(final Comparator<T> c) {
		return new Comparator<T>() {
			@Override
			public int compare(final T o1, final T o2) {
				return -c.compare(o1, o2);
			}
		};
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public boolean isDescendingSort() {
		return descendingSort;
	}

}
