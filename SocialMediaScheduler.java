import java.util.*;

// Base class (Encapsulation)
class Post {
    private String content;
    private Date scheduledTime;

    public Post(String content, Date scheduledTime) {
        this.content = content;
        this.scheduledTime = scheduledTime;
    }

    public String getContent() {
        return content;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    @Override
    public String toString() {
        return "Post: \"" + content + "\" scheduled at " + scheduledTime;
    }
}

// Abstraction
abstract class PostScheduler {
    protected List<Post> scheduledPosts = new ArrayList<>();

    public void addPost(Post post) {
        scheduledPosts.add(post);
        schedulePost(post); // Polymorphism
    }

    public void showScheduledPosts() {
        if (scheduledPosts.isEmpty()) {
            System.out.println("No posts scheduled.");
        } else {
            for (Post post : scheduledPosts) {
                System.out.println(post);
            }
        }
    }

    // Abstract method (to be implemented by subclasses)
    public abstract void schedulePost(Post post);
}

// Inheritance + Polymorphism
class FacebookScheduler extends PostScheduler {
    @Override
    public void schedulePost(Post post) {
        System.out.println("✅ Facebook: " + post);
    }
}

class TwitterScheduler extends PostScheduler {
    @Override
    public void schedulePost(Post post) {
        System.out.println("✅ Twitter: " + post);
    }
}

class InstagramScheduler extends PostScheduler {
    @Override
    public void schedulePost(Post post) {
        System.out.println("✅ Instagram: " + post);
    }
}

// Main Application
public class SocialMediaScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Different schedulers (OOP)
        PostScheduler fbScheduler = new FacebookScheduler();
        PostScheduler twScheduler = new TwitterScheduler();
        PostScheduler igScheduler = new InstagramScheduler();

        while (true) {
            System.out.println("\n=== Social Media Post Scheduler ===");
            System.out.println("1. Schedule Facebook Post");
            System.out.println("2. Schedule Twitter Post");
            System.out.println("3. Schedule Instagram Post");
            System.out.println("4. View Scheduled Posts");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 5) {
                System.out.println("Exiting... Bye!");
                break;
            }

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter post content: ");
                    String content = sc.nextLine();
                    System.out.print("Enter delay in seconds to schedule: ");
                    int delay = sc.nextInt();
                    sc.nextLine();

                    Date scheduledTime = new Date(System.currentTimeMillis() + delay * 1000L);
                    Post post = new Post(content, scheduledTime);

                    if (choice == 1) fbScheduler.addPost(post);
                    else if (choice == 2) twScheduler.addPost(post);
                    else igScheduler.addPost(post);
                    break;

                case 4:
                    System.out.println("\n--- Facebook Posts ---");
                    fbScheduler.showScheduledPosts();
                    System.out.println("\n--- Twitter Posts ---");
                    twScheduler.showScheduledPosts();
                    System.out.println("\n--- Instagram Posts ---");
                    igScheduler.showScheduledPosts();
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }

        sc.close();
    }
}