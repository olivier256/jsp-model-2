package proxy;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.AssureUnique;

public class AssureUniquePager extends Pager<AssureUnique> {
	public AssureUniquePager(final List<AssureUnique> list) {
		super(list);
	}

	public AssureUniquePager(final List<AssureUnique> list, final int windowSize) {
		super(list, windowSize);
	}

	@Override
	public Map<String, Comparator<AssureUnique>> comparatorsList() {
		Map<String, Comparator<AssureUnique>> map = new HashMap<>();
		map.put("nomNaissance", new Comparator<AssureUnique>() {
			@Override
			public int compare(final AssureUnique o1, final AssureUnique o2) {
				return o1.getNomNaissance().compareTo(o2.getNomNaissance());
			}
		});
		map.put("prenomNaissance", new Comparator<AssureUnique>() {
			@Override
			public int compare(final AssureUnique o1, final AssureUnique o2) {
				return o1.getPrenomNaissance().compareTo(o2.getPrenomNaissance());
			}
		});
		map.put("dateDeNaissance", new Comparator<AssureUnique>() {
			@Override
			public int compare(final AssureUnique o1, final AssureUnique o2) {
				final Date date1 = extractDateDeNaissance(o1);
				final Date date2 = extractDateDeNaissance(o2);
				return date1.compareTo(date2);
			}

			private Date extractDateDeNaissance(final AssureUnique assureUnique) {
				GregorianCalendar gc1 = new GregorianCalendar();
				gc1.set(Calendar.YEAR, assureUnique.getAnneeNaissance());
				gc1.set(Calendar.MONTH, assureUnique.getMoisNaissance() - 1);
				gc1.set(Calendar.DAY_OF_MONTH, assureUnique.getJourNaissance());
				return gc1.getTime();
			}
		});
		return map;
	}

	@Override
	public AssureUnique[] listToArray(final List<AssureUnique> list) {
		return list.toArray(new AssureUnique[list.size()]);
	}

}
