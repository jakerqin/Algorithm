package com.qin.dataStructure.Tree.AVL;

public class AVLTree {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6,8, 9};
        //创建一个 AVLTree对象
        AVL avlTree = new AVL();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new node(arr[i]));
        }

        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();

        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前的根结点=" + avlTree.getRoot());
    }
}

class AVL{
    private node root;

    public node getRoot() {
        return root;
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

        // 当添加完一个节点后，如果（右子树的高度 - 左子树的高度） > 1,左旋转
        if (rightHeight() - leftHeight() > 1) {
            //当前节点的右子树的左子树的高度大于
            // 当前节点右子树的的右子树的高度，我们需要进行双旋转
            // 先对当前节点的右子树进行右旋转，再对当前节点进行左旋转
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightHeight();
                this.leftRatate();
            } else {
                this.leftRatate();
            }
            //已经平衡了不需要往下走了.向下继续判断还可能出现不可预料的错误
            return ;
        }

        // 当添加完一个节点后，如果（左子树的高度 - 右子树的高度） > 1,右旋转
        if (leftHeight() -rightHeight()  > 1) {
            // 当树为{10, 11, 7, 6,8, 9}这样类似的存在当前节点的左子树的右子树的高度大于
            // 当前节点左子树的的左子树的高度，我们需要进行双旋转
            // 先对当前节点的左子树进行左旋转，再对当前节点进行右旋转
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 对当前节点的左子树进行左旋转
                left.leftRatate();
                // 再对当前节点进行右旋转
                this.rightRatate();
            }else {
                this.rightRatate();
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
     * 得到树的深度
     * @return
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) +1;
    }

    /**
     * 左旋转方法
     */
    private void leftRatate() {
        // 新建节点为当前根节点的值
        node newnode = new node(value);
        // 把当前节点的左子树设置为新节点的左子树
        newnode.left = this.left;
        // 把新节点的右子树设置成当前节点的右子树的左子树
        newnode.right = this.right.left;
        // 把当前节点的值设置成右子树的值
        value = right.value;
        // 把当前节点的右子树设置成其右子树的右子树
        this.right = this.right.right;
        // 把当前节点的左子树设置成新节点
        this.left = newnode;


    }

    /**
     * 右旋转方法
     */
    public void rightRatate() {
        // 建立一个新节点，存放当前根节点的值
        node newnode = new node(value);
        // 新节点的右子树为当前节点的右子树
        newnode.right = this.right;
        // 新节点的左子树为当前节点的左子树的右子树
        newnode.left = this.left.right;
        // 当前节点的值设置为当前节点左子树的值
        this.value = this.left.value;
        // 当前节点的左子树设置成左子树的左子树
        this.left = this.left.left;
        // 当前节点的右子树设置成新节点
        this.right = newnode;
    }

    /**
     * 得到当前节点以左子树为根节点的高度
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 得到当前节点以右子树为根节点的高度
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
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
