import java.util.List;

public interface FeedbackDAO {
    void addFeedback(Feedback feedback);
    Feedback getFeedback(int feedbackId);
    List<Feedback> getAllFeedbacks();
    List<Feedback> getFeedbacksByUser(int userId);
    void deleteFeedback(int feedbackId);
}
