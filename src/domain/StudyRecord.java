package domain;

public class StudyRecord {
    private double grade;
    private CSE cse;

    public StudyRecord(CSE cse){
        this.cse = cse;
        this.grade = 0;
    }
    public StudyRecord(CSE cse, double grade){
        this.cse = cse;
        this.grade = grade;
    }

    public StudyRecord(Course course, Term term, double grade) {
        this.cse = new CSE(course, term);
        this.grade = grade;
    }

    public void setGrade(double grade){ this.grade = grade; }

    public double getGrade() { return grade; }
    public CSE getCse() { return cse; }
    public Term getTerm(){ return cse.getTerm(); }
    public Course getCourse(){ return cse.getCourse(); }
}
