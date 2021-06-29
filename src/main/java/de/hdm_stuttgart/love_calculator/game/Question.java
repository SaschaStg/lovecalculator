package de.hdm_stuttgart.love_calculator.game;

public class Question {
    public final int index;
    // Content: Questions
    public final String questionContent;
    public final String questionContentUser2;
    /** Basic / Advanced / Speed */
    private final String mode;
    /** Headline/Topic of the question */
    private final String category;
    /** x1<low> , 2x<medium>, 3X<high> */
    private final String priority;

    public Question(int index, String content, String content2, String mode, String category, String priority) {
        this.index = index;
        this.questionContent = content;
        this.questionContentUser2 = content2;
        this.mode = mode;
        this.category = category;
        this.priority = priority;
    }




    /*@Override
    public String toString() {
        return "Question{" +
                "questionContent='" + questionContent + '\'' +
                ", mode=" + mode +
                ", inputType=" + inputType +
                ", category='" + category +
                ", priority='" + priority + '\'' +
                '}';
    }*/
}
