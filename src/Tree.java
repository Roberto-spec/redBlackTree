public class Tree {
    private Node root;


    private Node getGrandFather(Node node){
        if(node.getParent()!=null){
            return node.getParent().getParent();
        }
        return null;

    }
    private Node getUncle(Node node){
        Node lol=node.getParent().getParent();
        if(lol.getLeft()==node.getParent()){
            return lol.getRight();
        }
        return lol.getLeft();

    }
    private void situation1(Node node){
        if(node==root){
            node.setRed(false);
        }
        else situation2(node);
    }
    private void situation2(Node node){
        if (node.getParent().isRed()) situation3(node);
    }
    private void situation3(Node node){
        Node uncle=getUncle(node);
        if(uncle!=null&& uncle.isRed()){
            uncle.getParent().setRed(true);
            node.getParent().setRed(false);
            uncle.setRed(false);
            situation1(uncle.getParent());
        }
        else situation4(node);
    }
    private void situation4(Node node){
        Node grandF=getGrandFather(node);
        Node parent=node.getParent();
        Node anode=node;
        if(node==node.getParent().getLeft()&&grandF.getRight()==node.getParent()) {
            Node m=node.getLeft();
            grandF.setLeft(node);
            node.setParent(grandF);
            node.setLeft(parent);
            parent.setParent(node);
            parent.setRight(m);
            if(m!=null) m.setParent(parent);
            anode=parent;

        }
        situation5(anode);

    }
    private void situation5(Node node){
        Node parent=node.getParent();
        Node grandF=getGrandFather(node);
        Node grandfParent=getGrandFather(parent);

        if(grandF.getRight()==parent){
            parent.setRed(false);
            grandF.setRed(false);
            Node m=parent.getLeft();
            parent.setLeft(grandF);
            grandF.setParent(parent);
            grandF.setRight(m);
            if(m!=null)m.setParent(grandF);
            if(grandfParent==null) root=parent;
            else{
                if(grandfParent.getRight()==grandF) grandfParent.setRight(parent);
                else grandfParent.setLeft(parent);
                parent.setParent(grandfParent);

            }
        }
        else{
            parent.setRed(false);
            grandF.setRed(true);
            Node m=parent.getRight();
            parent.setRight(grandF);
            grandF.setParent(parent);
            grandF.setLeft(m);
            if(m!=null)m.setParent(grandF);
            if(grandfParent==null) root=parent;
            else{
                if(grandfParent.getLeft()==grandF) grandfParent.setLeft(parent);
                else {grandfParent.setRight(parent);}
                parent.setParent(grandfParent);
            }

        }
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    private Node findNode(Node node,int key){
        if(key<node.getKey()){
            if(node.getLeft()==null) return node;
            else return findNode(node.getLeft(),key);
        }
        else{
            if(node.getRight()==null) return node;
            else return findNode(node.getRight(),key);
        }
    }
    public void add(int key){
        Node node;
        if(root==null){
            root=new Node(key);
            node=root;
        }
        else{
            Node parent=findNode(root,key);
            node=new Node(key);
            if(key< parent.getKey()) parent.setLeft(node);
            else parent.setRight(node);
            node.setParent(parent);
        }
        situation1(node);
    }

    @Override
    public String toString() {
        return "Tree{" +
                "root=" + root +
                '}';
    }
}
