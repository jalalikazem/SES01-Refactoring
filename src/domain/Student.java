package domain;
import java.util.ArrayList;
import java.util.List;

public class Student {
	private String id;
	private String name;

	static class CourseSection {
        CourseSection(Course course, int section) {
            this.course = course;
            this.section = section;
        }
        Course course;
	    int section;
    }

	private List<StudyRecord> studyRecords;
	private List<CourseSection> currentTerm;

	public Student(String id, String name) {
		this.id = id;
		this.name = name;
		this.studyRecords = new ArrayList<>();
		this.currentTerm = new ArrayList<>();
	}
	
	public void takeCourse(Course c, int section) {
		currentTerm.add(new CourseSection(c, section));
	}

	public List<StudyRecord> getStudyRecords() {
		return studyRecords;
	}

	public void addStudyRecordItem(Course course, Term term, double grade) {
		for(StudyRecord stdRecord : getStudyRecords()){
			if(stdRecord.getCse().getCourse().equals(course) && stdRecord.getCse().getTerm().equals(term))
				stdRecord.setGrade(grade);
		}
		getStudyRecords().add(new StudyRecord(course, term, grade));
    }

    public List<CourseSection> getCurrentTerm() {
        return currentTerm;
    }

    public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}

	public boolean hasPassed(Course course){
		for(StudyRecord studyRecord : getStudyRecords()){
			if(studyRecord.getCse().getCourse().equals(course) && studyRecord.getGrade() >= 10)
				return true;
		}
		return false;
	}

	public double calculateGpa() {
		double points = 0;
		int totalUnits = 0;
		for (StudyRecord sr : getStudyRecords()) {
			points += sr.getGrade() * sr.getCourse().getUnits();
			totalUnits += sr.getCourse().getUnits();
		}
		return points / totalUnits;
	}
}
