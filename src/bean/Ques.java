package bean;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ques {
    int qid;
    String qname;
//    java.util.Map<Integer,Ans> anses;
//    java.util.Set<Ans> anses;
    Ans[] anses;
    
    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public Ans[] getAnses() {
        return anses;
    }

    public void setAnses(Ans[] anses) {
        this.anses = anses;
    }

    
    
    
}
