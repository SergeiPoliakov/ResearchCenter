package com.netcracker.unc.priorityModule;

public class ModelForTable {

	private String name;
	private double value;
	private String parentName;// скорее всего не нужно, будет сортированная
								// коллекция

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
