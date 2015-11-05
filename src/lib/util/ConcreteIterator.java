package lib.util;

import java.util.List;

@SuppressWarnings("rawtypes")
public class ConcreteIterator implements LocalIterator, java.io.Serializable {

	private static final long serialVersionUID = -3916510859011793285L;
	
	private List list = null;
	private int index = -1;
	
	public ConcreteIterator(List list) {

		this.list = list;
		this.index = 0;
	}

	public boolean hasNext() {

		if (list != null) {
			return list.size() > index;
		} else {
			return false;
		}
	}

	public Object next() {

		if (list != null) {
			return list.get(index++);
		} else {
			return null;
		}
	}

	public void remove() {
	}

	public void close() {
	}
}
