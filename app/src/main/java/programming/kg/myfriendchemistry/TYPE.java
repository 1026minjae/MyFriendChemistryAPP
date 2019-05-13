package programming.kg.myfriendchemistry;

import java.io.Serializable;

/**
 * Created by MJ.KIM on 2017-05-07.
 */

public class TYPE {
    static class QMC implements Serializable
    {
        public String question;
        public String a1;
        public String a2;
        public String a3;
        public String a4;
        public String a5;
        public String answer;
    }//객관식 문제 클래스
    static class QSQ implements Serializable
    {
        public String question;
        public String answer;
    }//주관식 문제 클래스
}
