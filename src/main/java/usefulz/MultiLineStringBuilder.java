package usefulz;

import java.util.Arrays;

public class MultiLineStringBuilder implements java.io.Serializable {

	public static final String EMPTY_LINE = "";

	protected final int capacity;
	protected final String[] lines;

	protected int pointer;

	// ToString cache
	protected String toStringCache;

	public MultiLineStringBuilder() {
		this(32);
	}

	public MultiLineStringBuilder(int capacity) {
		this.capacity = capacity;
		this.lines = new String[capacity];
		this.pointer = 0;

		Arrays.fill(lines, EMPTY_LINE);
	}

	public int pointer() {
		return this.pointer;
	}

	public MultiLineStringBuilder append(String str) {
		toStringCache = null;
		lines[pointer] += str;
		return this;
	}

	public MultiLineStringBuilder append(Object obj) {
		return append(String.valueOf(obj));
	}

	public MultiLineStringBuilder append(String str, int line) {
		atLine(line);
		return append(str);
	}

	public MultiLineStringBuilder append(Object obj, int line) {
		atLine(line);
		return append(obj);
	}

	public MultiLineStringBuilder substr(int beginIndex) {
		toStringCache = null;
		lines[pointer] = lines[pointer].substring(beginIndex);
		return this;
	}

	public MultiLineStringBuilder substr(int beginIndex, int endIndex) {
		toStringCache = null;
		lines[pointer] = lines[pointer].substring(beginIndex, endIndex);
		return this;
	}

	public MultiLineStringBuilder substr(int beginIndex, int endIndex, int line) {
		atLine(line);
		return substr(beginIndex, endIndex);
	}

	public MultiLineStringBuilder clear() {
		toStringCache = null;
		lines[pointer] = new String();
		return this;
	}

	public MultiLineStringBuilder clearLine(int line) {
		atLine(line);
		return clear();
	}

	public MultiLineStringBuilder atLine(int pointer) {
		if( pointer < 0 || pointer > capacity) throw new ArrayIndexOutOfBoundsException("Out of bounds! Capacity: "+capacity);

		this.pointer = pointer;
		return this;
	}

	public MultiLineStringBuilder nextLine(int count) {
		return atLine(pointer+count);
	}

	public MultiLineStringBuilder nextLine() {
		return nextLine(1);
	}

	public MultiLineStringBuilder previousLine(int count) {
		return nextLine(-count);
	}

	public MultiLineStringBuilder previousLine() {
		return previousLine(1);
	}

	public String toStringLine(int line) {
		return lines[line];
	}

	public String toString() {
		if(toStringCache == null) {
			final StringBuilder sb = new StringBuilder();
			for(String str : lines) sb.append(str).append("\n");
			toStringCache = sb.toString().trim();
		}
		return toStringCache;
	}

}