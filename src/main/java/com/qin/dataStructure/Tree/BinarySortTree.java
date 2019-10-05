package com.qin.dataStructure.Tree;

/**
 * 二叉排序树（BST）
 */
public class BinarySortTree {
    private node root;

    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for(int i = 0; i< arr.length; i++) {
            binarySortTree.add(new node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder();

        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(7);
        binarySortTree.delNode(2);
        binarySortTree.delNode(3);
        binarySortTree.delNode(9);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);

        binarySortTree.infixOrder();

    }

    /**
     * 二叉树的添加节点的方法
     * @param node
     */
    public void add(node node) {
        if (root == null) {
            root = node;
        }else{
            root.add(node);
        }
    }

    /**
     * 树的中序遍历
     *
     */
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("tree is empty!");
        }
    }

    /**
     * 查找要删除的节点
     * @param value
     * @return
     */
    public node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    /**
     * 查找要删除节点的父节点
     * @param value
     * @return
     */
    public node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 如果我们发现当前这个二叉树只有一个节点
            if (root.left == null && root.right ==null) {
                root =null;
                return ;
            }
            node curNode = root.search(value);
            // 判断是否存在此节点
            if (curNode == null) {
                return ;
            }
            node parentNode = root.searchParent(value);
            // 如果删除的是叶子结点
            if (curNode.left == null && curNode.right == null) {
                if (parentNode.left != null && parentNode.left.value == value) {
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value){
                    parentNode.right = null;
                }
            }
            // 如果删除的是有一个子节点的节点
            else if (curNode.left == null || curNode.right == null) {
                /*node temp = curNode.left == null ? curNode.right : curNode.left;
                if (parentNode.left.value == value) {
                    parentNode.left = temp;
                } else {
                    parentNode.right = temp;
                }
                当存在像10（作为父节点） -》 1 删除10这个节点的时候，没有父节点，父节点为null 所以空指针
                所以需要对父节点进行判断
                */

                //如果要删除的结点有左子结点
                if(curNode.left != null) {
                    if(parentNode != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if(parentNode.left.value == value) {
                            parentNode.left = curNode.left;
                        } else { //  targetNode 是 parent 的右子结点
                            parentNode.right = curNode.left;
                        }
                    } else {
                        root = curNode.left;
                    }
                } else { //如果要删除的结点有右子结点
                    if(parentNode != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if(parentNode.left.value == value) {
                            parentNode.left = curNode.right;
                        } else { //如果 targetNode 是 parent 的右子结点
                            parentNode.right = curNode.right;
                        }
                    } else {
                        root = curNode.right;
                    }
                }
            } else {
                // 删除有两个子节点的节点
                int minValue = delRightTreeMin(curNode.right);
                curNode.value = minValue;
            }


        }

    }

    public int delRightTreeMin(node node) {
        node targetNode = node;
        // 查找最小的节点
        while (targetNode.left != null) {
            targetNode = targetNode.left;
        }
        delNode(targetNode.value);
        return targetNode.value;
    }

}

/**
 * 此是节点类
 */
class node{
    int value;
    node left;
    node right;

    public node(int value) {
        this.value = value;
    }

    /**
     * 添加节点
     * @param node
     */
    public void add(node node) {
        if (node == null ) return ;

        // 递归判断添加在何处
        if (node.value > this.value) {
            // 如果当前节点的有节点为空，就添加在此
            if (this.right == null) {
                this.right = node;
            }else{
                this.right.add(node);
            }
        }else {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }

    }

    /**
     * 查找要删除的节点
     * @param value
     * @return
     */
    public node search(int value) {
        if (this.value == value) {
            return this;
        } else if(value < this.value){
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(value);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(value);
            }
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value
     * @return
     */
    public node searchParent(int value) {

        if (this.left != null && this.left.value == value || (this.right != null && this.right.value == value)){
            return this;
        } else if (value < this.value && this.left != null) {
            return this.left.searchParent(value);
        } else if (value >= this.value && this.right != null) {
            return this.right.searchParent(value);
        } else {
            return null;
        }
    }






    @Override
    public String toString() {
        return "node{" +
                "value=" + value +
                '}';
    }
}
