package ejercicioEntregable_TP2;

import java.util.ArrayList;

public class TreeNode {

	private Integer value;
	private TreeNode left;
	private TreeNode right;

	public TreeNode(Integer value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public Integer getValue() {
		return value;
	}

	public boolean hasLeft() {
		return left != null;
	}

	public boolean hasRight() {
		return right != null;
	}

	public boolean hasChild() {
		return hasLeft() || hasRight();
	}
	
	public boolean hasChilds() {
		return hasLeft() && hasRight();
	}

	/**
	 * 
	 * @param e elemento buscado
	 * @return true si el elemento existe
	 */
	public boolean hasElem(Integer e) {
		boolean find = value == e;
		if (value > e && !find && hasLeft())
			find = left.hasElem(e);

		if (value < e && !find && hasRight())
			find = right.hasElem(e);

		return find;
	}

	/**
	 * 
	 * @param h altura del nodo actual
	 * @return altura maxima del arbol
	 */
	public Integer getHeight(int h) {
		int hLeft = h, hRight = h;
		if (hasLeft())
			hLeft = left.getHeight(h + 1);

		if (hasRight())
			hRight = right.getHeight(h + 1);

		return (hLeft > hRight) ? hLeft : hRight;
	}

	/**
	 * 
	 * @return elemento mas a la derecha del arbol
	 */
	public Integer getMaxElem() {
		return (hasRight()) ? right.getMaxElem() : value;
	}

	/**
	 * 
	 * @param level nivel donde se buscan elementos
	 * @param h     altura del nodo actual
	 * @return lista de elementos en el nivel buscado
	 */
	public ArrayList<Integer> getElementAtLevel(Integer level, Integer h) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (level == h) {
			list.add(value);
		} else {
			if (hasLeft()) {
				list.addAll(left.getElementAtLevel(level, h + 1));
			}
			if (hasRight()) {
				list.addAll(right.getElementAtLevel(level, h + 1));
			}
		}
		return list;
	}

	/**
	 * 
	 * @return lista de 'hojas'
	 */
	public ArrayList<Integer> getFrontera() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (!hasChild()) {
			list.add(value);
		} else {
			if (hasLeft()) {
				list.addAll(left.getFrontera());
			}
			if (hasRight()) {
				list.addAll(right.getFrontera());
			}
		}
		return list;
	}

	/**
	 * 
	 * @return lista de elementos de la rama mas grande
	 */
	public ArrayList<Integer> getLongestBranch() {
		ArrayList<Integer> listL = new ArrayList<Integer>();
		ArrayList<Integer> listR = new ArrayList<Integer>();
		listL.add(value);
		listR.add(value);
		
		if (hasLeft()) {
			listL.addAll(left.getLongestBranch());
		}
		if (hasRight()) {
			listR.addAll(right.getLongestBranch());
		}

		return (((listL.size() >= listR.size()) ? listL : listR));
	}

	/*************************** Metodos print() **************************/

	public String printPreOrder() {
		String info = " " + value;

		if (hasLeft())
			info += left.printPreOrder();
		else
			info += " - ";

		if (hasRight())
			info += right.printPreOrder();
		else
			info += " - ";

		return info;
	}

	public String printInOrder() {
		String info = "";
		if (hasLeft())
			info += left.printInOrder();

		info += value;

		if (hasRight())
			info += right.printInOrder();

		return info;
	}

	public String printPosOrder() {
		String info = "";
		if (hasLeft())
			info += left.printPosOrder();

		if (hasRight())
			info += right.printPosOrder();

		return info += value;
	}

}
