package dataAbstractionLearning.protectedLearning;

public class StudentProtected {

    protected int sno;

    protected String fname;

    protected String lname;

    protected void display(){
        System.out.println("Inside the Student: display method ");
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
