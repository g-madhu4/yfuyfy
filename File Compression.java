import java.lang.*;
import java.util.*;
class main{
    public static void main(String[] args) throws Exception{
        String aa="aab";
        huffman hf=new huffman(aa);
        String encode= hf.encode(aa);
        System.out.println(encode);
        String decode= hf.decode(encode);
        System.out.println(decode);

    }
}
class huffman{
HashMap<Character,String>encoder;
HashMap<String,Character>decoder;
private class node implements Comparable<node>{
    Character data;
    int cost;
    node left;
    node right;
    public node(Character data,int cost){
        this.data=data;
        this.cost=cost;
        this.left=null;
        this.right=null;
    }
    public int compareTo(node other){
        return this.cost-other.cost;
    }
}
public huffman(String feeder) throws Exception{
    HashMap<Character,Integer>fmap=new HashMap<>();
    for(int i=0;i<feeder.length();i++){
        char cc=feeder.charAt(i);
        fmap.putIfAbsent(cc,0);
        fmap.put(cc,fmap.get(cc)+1);
    }
    Heap<node>minheap=new Heap<>();

    Set<Map.Entry<Character,Integer>>entrySet= fmap.entrySet();
    for(Map.Entry<Character,Integer>entry:entrySet ) {
        node node = new node(entry.getKey(), entry.getValue());
        minheap.insert(node);
    }
    while(minheap.size()!=1){
        node first=minheap.remove();
        node second=minheap.remove();
        node newnode=new node('\0',first.cost+second.cost);
        newnode.left=first;
        newnode.right=second;
        minheap.insert(newnode);

    }
    node ft=minheap.remove();
    this.encoder=new HashMap<>();
    this.decoder=new HashMap<>();
    this.iniEncoder(ft,"");
}
public void iniEncoder(node node,String osf){
    if(node==null)
    {
        return;
    }
    if(node.left==null&&node.right==null){
        this.encoder.put(node.data,osf);
        this.decoder.put(osf,node.data);
    }

    iniEncoder(node.left,osf+"0");
    iniEncoder(node.right,osf+"1");
}
public String encode(String data) {
    String ans = "";
    for (int i = 0; i < data.length(); i++) {
        ans += encoder.get(data.charAt(i));
    }
    return ans;
}
public String decode(String data){
    String ans="";
    String res="";
    for(int i=0;i<data.length();i++){
        ans+=data.charAt(i);
        if(decoder.containsKey(ans)){
            res+=decoder.get(ans);
            ans="";
        }
    }
    return res;
}



 }

class Heap<T extends Comparable<T>> {

    private ArrayList<T> list;

    public Heap() {
        list = new ArrayList<>();
    }

    private void swap(int first, int second) {
        T temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private int left(int index) {
        return index * 2 + 1;
    }

    private int right(int index) {
        return index * 2 + 2;
    }

    public void insert(T value) {
        list.add(value);
        upheap(list.size() - 1);
    }

    private void upheap(int index) {
        if (index == 0) {
            return;
        }
        int p = parent(index);
        if (list.get(index).compareTo(list.get(p)) < 0) {
            swap(index, p);
            upheap(p);
        }
    }

    public T remove() throws Exception {
        if (list.isEmpty()) {
            throw new Exception("Removing from an empty heap!");
        }

        T temp = list.get(0);

        T last = list.remove(list.size() - 1);
        if (!list.isEmpty()) {
            list.set(0, last);
            downheap(0);
        }

        return temp;
    }

    private void downheap(int index) {
        int min = index;
        int left = left(index);
        int right = right(index);

        if (left < list.size() && list.get(min).compareTo(list.get(left)) > 0) {
            min = left;
        }

        if (right < list.size() && list.get(min).compareTo(list.get(right)) > 0) {
            min = right;
        }

        if (min != index) {
            swap(min, index);
            downheap(min);
        }
    }
    public ArrayList<T> heapSort() throws Exception {
        ArrayList<T> data = new ArrayList<>();
        while(!list.isEmpty()) {
            data.add(this.remove());
        }
        return data;
    }

    public int size() {
       return list.size();
    }
}