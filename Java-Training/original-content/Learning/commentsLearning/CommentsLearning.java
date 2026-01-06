//Before package i want to include my comments
package commentsLearning;

import java.util.Date;

// This line is to comment outside the class
public class CommentsLearning {

    // This variable to accept Integer number
    int a;

    //Single line comment
    //This is to learn more liner description
    //One more comment after this

    //Multiline comment
    /*
    This is to learn more liner description
    One more comment after this
     */

    // This class is to learn comments
    public static void main(String[] args) {
        System.out.println("Comments Learning");
        CommentsLearning commentsLearning = new CommentsLearning();
        commentsLearning.display();
    }

    // Siva - 06/14
    public void display(){
        Date date = new Date();
        System.out.println(date);
    }
}

// After } Paren this
