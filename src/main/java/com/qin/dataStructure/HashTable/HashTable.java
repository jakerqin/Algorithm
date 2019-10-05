package com.qin.dataStructure.HashTable;

public class HashTable {

    public static void main(String[] args) {
        hashTableDemo hashtable = new hashTableDemo(7);
        hashtable.add(new Emp(1,"qin"));  // 2
        hashtable.add(new Emp(13,"wnag")); // 7
        hashtable.add(new Emp(8,"liu")); // 2
        hashtable.add(new Emp(20,"lin")); // 7
        hashtable.list();
    }
}



// 表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;

    public Emp(int id,String name) {
        this.id = id;
        this.name = name;
    }
}

// 创建EmpLinkedList ,表示链表
class EmpLinkedList{
    private Emp head;

    /**
     * 添加雇员到链表
     * @param emp
     */
    public void add(Emp emp) {
        if (head == null) {
            head = emp;
            return ;
        }
        Emp curEmp = head;
        while (curEmp.next != null) {
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 遍历链表节点
     * @param no
     */
    public void list(int no) {
        if (head == null) {
            System.out.println("第 "+ (no+1) + " 个链表为空");
            return ;
        }
        Emp curEmp = head;
        System.out.print("第 "+(no+1)+" 链表的信息为");
        while (curEmp != null) {
            System.out.printf(" => id=%d name=%s\t", curEmp.id, curEmp.name);
            curEmp = curEmp.next;
        }
        System.out.println();
    }

    /**
     * 根据id查找雇员
     * 如果查找到，就返回Emp, 如果没有找到，就返回null
     * @param id
     * @return
     */
    public Emp findEmpById(int id) {
        //判断链表是否为空
        if(head == null) {
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true) {
            if(curEmp.id == id) {//找到
                break;//这时curEmp就指向要查找的雇员
            }
            //退出
            if(curEmp.next == null) {//说明遍历当前链表没有找到该雇员
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//以后
        }

        return curEmp;
    }
}

class hashTableDemo{
    private int size;
    private EmpLinkedList[] listArray;

    public hashTableDemo(int size) {
        this.size = size;
        listArray = new EmpLinkedList[size];
        for (int i = 0; i < size; i++) {
            listArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 求hash值的
     * @param id
     * @return
     */
    public int hashFun(int id) {
        return id % size;
    }

    /**
     * 添加元素
     * @param emp
     */
    public void add(Emp emp) {
        int hashcode = hashFun(emp.id);
        listArray[hashcode].add(emp);
    }

    /**
     * 遍历所有链表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            listArray[i].list(i);
        }
    }

    public void findById(int id) {
        int hashcode = hashFun(id);
        Emp targetEmp = listArray[hashcode].findEmpById(id);
        if (targetEmp != null) {
            System.out.printf("在第%d条链表中找到 雇员 id = %d\n", (hashcode + 1), id);
        }else{
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }
}
