package domain;

import java.util.List;

import domain.exceptions.EnrollmentRulesViolationException;

public class EnrollCtrl {
	public void enroll(Student s, List<CSE> courses) throws EnrollmentRulesViolationException {
		checkForAlreadyPassedCourse(s, courses);
		checkForPrerequisites(s, courses);
		checkForDuplicateEnroll(courses);
		checkForExamTimeConflict(courses);

		int unitsRequested = getUnitsRequested(courses);
		double gpa = s.calculateGpa();
		checkForGPAConstraint(unitsRequested, gpa);

		for (CSE o : courses)
			s.takeCourse(o.getCourse(), o.getSection());
	}

	private void checkForGPAConstraint(int unitsRequested, double gpa) throws EnrollmentRulesViolationException {
		if ((gpa < 12 && unitsRequested > 14) ||
				(gpa < 16 && unitsRequested > 16) ||
				(unitsRequested > 20))
			throw new EnrollmentRulesViolationException(String.format("Number of units (%d) requested does not match GPA of %f", unitsRequested, gpa));
	}

	private int getUnitsRequested(List<CSE> courses) {
		int unitsRequested = 0;
		for (CSE o : courses)
			unitsRequested += o.getCourse().getUnits();
		return unitsRequested;
	}



	private void checkForAlreadyPassedCourse(Student s, List<CSE> courses) throws EnrollmentRulesViolationException {
		for (CSE o : courses) {
			if (s.hasPassed(o.getCourse()))
				throw new EnrollmentRulesViolationException(String.format("The student has already passed %s", o.getCourse().getName()));
		}
	}

	private void checkForPrerequisites(Student s, List<CSE> courses) throws EnrollmentRulesViolationException {
		for (CSE o : courses) {
			List<Course> prereqs = o.getCourse().getPrerequisites();
			for (Course pre : prereqs) {
				if (!s.hasPassed(pre))
					throw new EnrollmentRulesViolationException(String.format("The student has not passed %s as a prerequisite of %s", pre.getName(), o.getCourse().getName()));
			}
		}
	}

	private void checkForDuplicateEnroll(List<CSE> courses) throws EnrollmentRulesViolationException {
		for (CSE o : courses) {
            for (CSE o2 : courses) {
                if (o == o2)
                    continue;
                if (o.getCourse().equals(o2.getCourse()))
                    throw new EnrollmentRulesViolationException(String.format("%s is requested to be taken twice", o.getCourse().getName()));
            }
		}
	}

	private void checkForExamTimeConflict(List<CSE> courses) throws EnrollmentRulesViolationException {
		for (CSE o : courses) {
			for (CSE o2 : courses) {
				if (o == o2)
					continue;
				if (o.hasExamTimeConflict(o2))
					throw new EnrollmentRulesViolationException(String.format("Two offerings %s and %s have the same exam time", o, o2));
			}
		}
	}
}
