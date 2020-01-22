package proxy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Tache;

public class TachePager extends Pager<Tache> {

	public TachePager(final List<Tache> list) {
		super(list);
	}

	@Override
	public Map<String, Comparator<Tache>> comparatorsList() {
		Map<String, Comparator<Tache>> map = new HashMap<>();
		map.put("responsable", new Comparator<Tache>() {

			@Override
			public int compare(final Tache o1, final Tache o2) {
				return compareUp(o1.getResponsable(), o2.getResponsable());
			}

		});
		map.put("statut", new Comparator<Tache>() {

			@Override
			public int compare(final Tache o1, final Tache o2) {
				return compareUp(o1.getStatut(), o2.getStatut());
			}

		});
		map.put("type", new Comparator<Tache>() {

			@Override
			public int compare(final Tache o1, final Tache o2) {
				return o1.getType().compareTo(o2.getType());
			}

		});
		map.put("pays", new Comparator<Tache>() {

			@Override
			public int compare(final Tache o1, final Tache o2) {
				return compareUp(o1.getPays(), o2.getPays());
			}

		});
		return map;
	}

	public static <T> int compareUp(final Comparable<T> o1, final T o2) {
		if (o1 == null) {
			if (o2 == null) {
				return 0;
			} else {
				return -1;
			}
		} else {
			if (o2 == null) {
				return 1;
			} else {
				return o1.compareTo(o2);
			}
		}
	}

	@Override
	public Tache[] listToArray(final List<Tache> list) {
		return list.toArray(new Tache[list.size()]);
	}

}
