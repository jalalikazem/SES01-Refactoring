package domain;
import java.util.Date;

public class CSE {
	private Course course;
	private int section;
	private Date examDate;

	private Term term;

	public CSE(Course course, Term term) {
		this.course = course;
		this.section = 1;
		this.examDate = null;
		this.term = term;
	}

	public Term getTerm() { return term; }

	public CSE(Course course) {
		this.course = course;
		this.section = 1;
		this.examDate = null;
	}

	public CSE(Course course, Date examDate) {
		this.course = course;
		this.section = 1;
		this.examDate = examDate;
	}

	public CSE(Course course, Date examDate, int section) {
		this.course = course;
		this.section = section;
		this.examDate = examDate;
	}

	public Course getCourse() {
		return course;
	}
	
	public String toString() {
		return course.getName() + " - " + section;
	}
	
	public Date getExamTime() {
		return examDate;
	}

	public int getSection() { return section; }
	public boolean hasExamTimeConflict(CSE cse2){
		return getExamTime().equals(cse2.getExamTime());
	}
}
