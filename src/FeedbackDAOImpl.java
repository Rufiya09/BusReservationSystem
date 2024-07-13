import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedbackDAOImpl implements FeedbackDAO {
    private Map<Integer, Feedback> feedbackMap = new HashMap<>();
    private int feedbackIdCounter = 1;

    @Override
    public void addFeedback(Feedback feedback) {
        feedback.setId(feedbackIdCounter++);
        feedbackMap.put(feedback.getId(), feedback);
    }

    @Override
    public Feedback getFeedback(int feedbackId) {
        return feedbackMap.get(feedbackId);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbackMap.values());
    }

    @Override
    public List<Feedback> getFeedbacksByUser(int userId) {
        List<Feedback> userFeedbacks = new ArrayList<>();
        for (Feedback feedback : feedbackMap.values()) {
            if (feedback.getUserId() == userId) {
                userFeedbacks.add(feedback);
            }
        }
        return userFeedbacks;
    }

    @Override
    public void deleteFeedback(int feedbackId) {
        feedbackMap.remove(feedbackId);
    }
}
