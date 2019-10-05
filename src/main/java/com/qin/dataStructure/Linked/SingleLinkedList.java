package com.qin.dataStructure.Linked;

/**
 * 假设做一个水浒英雄顺序单链表
 *
 */
public class SingleLinkedList {
    private SingleNode head = new SingleNode(0, "", "");


    public void addNode(SingleNode node) {
        if (head.next == null) {
            head.next = node;
            return ;
        }
        SingleNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    public void addByOrder(SingleNode node) {
        if (head.next == null) {
            head.next = node;
            return ;
        }
        SingleNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id > node.id){
                break;
            }
            if (temp.next.id == node.id){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            System.out.println("节点已经存在");
        }else{
            node.next = temp.next;
            temp.next = node;
        }


    }
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
            return ;
        }
        SingleNode temp = head;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void deleteNode(int id){
        if (head.next == null) {
            return ;
        }
        SingleNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) break;

            if (temp.next.id == id) {
                flag = true;
                break;
            }
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("链表中不存在此节点！，无需删除");
        }
    }

    public static void main(String[] args) {
        SingleNode hero1 = new SingleNode(1, "宋江", "及时雨");
        SingleNode hero2 = new SingleNode(2, "卢俊义", "玉麒麟");
        SingleNode hero3 = new SingleNode(3, "吴用", "智多星");
        SingleNode hero4 = new SingleNode(4, "林冲", "豹子头");
        SingleLinkedList node = new SingleLinkedList();

        node.addByOrder(hero3);
        node.addByOrder(hero1);
        node.addByOrder(hero4);
        node.list();
    }


}
class SingleNode{
    public int id;
    public String name;
    public String alias;
    public SingleNode next;
    public SingleNode(int id, String name, String alias) {
        this.id = id;
        this.alias = alias;
        this.name  = name;
    }

    @Override
    public String toString() {
        return "SingleNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                '}';
    }
}
