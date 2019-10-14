package com.example.my.bean;

import java.util.List;

/**
 * Created by  wsl
 * on 2019/8/12 16:33
 * 试卷 实体类
 */
public class Page {

    /**
     * addressid : null
     * Eid : null
     * Paper_Id : 12
     * Paper_Title : 开始测验 不合格的发扫厕所一个月
     * DXList : null
     * PDList : null
     * DuoXList : null
     * QuesXList : [{"Ques_Type":"单选题","Ques_Code":"20","Ques_Id":46,"Ques_Name":"我是单选","Answer":null,"Lib_Id":0,"Judge_Score":0,"Single_Score":0,"Multi_Score":0,"UserAnswer":null,"TrueAnswer":null,"Answer_Code":null,"chle":[{"Ques_Id":0,"Answer_Id":233,"Answer_Code":"A","Answer_Content":"aaaaa","Remarks":null},{"Ques_Id":0,"Answer_Id":234,"Answer_Code":"B","Answer_Content":"bbbbbb","Remarks":null},{"Ques_Id":0,"Answer_Id":235,"Answer_Code":"C","Answer_Content":"vvvvvv","Remarks":null},{"Ques_Id":0,"Answer_Id":236,"Answer_Code":"D","Answer_Content":"dddddd","Remarks":null}]},{"Ques_Type":"单选题","Ques_Code":"20","Ques_Id":47,"Ques_Name":"这是单选啊  傻子","Answer":null,"Lib_Id":0,"Judge_Score":0,"Single_Score":0,"Multi_Score":0,"UserAnswer":null,"TrueAnswer":null,"Answer_Code":null,"chle":[{"Ques_Id":0,"Answer_Id":237,"Answer_Code":"A","Answer_Content":"ttttt","Remarks":null},{"Ques_Id":0,"Answer_Id":238,"Answer_Code":"B","Answer_Content":"uuuuu","Remarks":null},{"Ques_Id":0,"Answer_Id":239,"Answer_Code":"C","Answer_Content":"uuujjj","Remarks":null},{"Ques_Id":0,"Answer_Id":240,"Answer_Code":"D","Answer_Content":"jjghgh","Remarks":null}]},{"Ques_Type":"判断题","Ques_Code":"10","Ques_Id":42,"Ques_Name":"硕磊是个大傻子","Answer":null,"Lib_Id":0,"Judge_Score":0,"Single_Score":0,"Multi_Score":0,"UserAnswer":null,"TrueAnswer":null,"Answer_Code":null,"chle":[{"Ques_Id":0,"Answer_Id":215,"Answer_Code":"对","Answer_Content":"对","Remarks":null},{"Ques_Id":0,"Answer_Id":216,"Answer_Code":"错","Answer_Content":"错","Remarks":null}]},{"Ques_Type":"判断题","Ques_Code":"10","Ques_Id":43,"Ques_Name":"硕磊长得很难看","Answer":null,"Lib_Id":0,"Judge_Score":0,"Single_Score":0,"Multi_Score":0,"UserAnswer":null,"TrueAnswer":null,"Answer_Code":null,"chle":[{"Ques_Id":0,"Answer_Id":217,"Answer_Code":"对","Answer_Content":"对","Remarks":null},{"Ques_Id":0,"Answer_Id":218,"Answer_Code":"错","Answer_Content":"错","Remarks":null}]},{"Ques_Type":"多选题","Ques_Code":"30","Ques_Id":44,"Ques_Name":"你猜猜那个是正确答案","Answer":null,"Lib_Id":0,"Judge_Score":0,"Single_Score":0,"Multi_Score":0,"UserAnswer":null,"TrueAnswer":null,"Answer_Code":null,"chle":[{"Ques_Id":0,"Answer_Id":219,"Answer_Code":"A","Answer_Content":"我会A","Remarks":null},{"Ques_Id":0,"Answer_Id":220,"Answer_Code":"B","Answer_Content":"我是B","Remarks":null},{"Ques_Id":0,"Answer_Id":221,"Answer_Code":"C","Answer_Content":"我是C","Remarks":null},{"Ques_Id":0,"Answer_Id":222,"Answer_Code":"D","Answer_Content":"我是D","Remarks":null},{"Ques_Id":0,"Answer_Id":223,"Answer_Code":"E","Answer_Content":"我是e","Remarks":null},{"Ques_Id":0,"Answer_Id":224,"Answer_Code":"F","Answer_Content":"我是F","Remarks":null},{"Ques_Id":0,"Answer_Id":225,"Answer_Code":"G","Answer_Content":"我是g","Remarks":null}]},{"Ques_Type":"多选题","Ques_Code":"30","Ques_Id":45,"Ques_Name":"猜猜那个是错误答案","Answer":null,"Lib_Id":0,"Judge_Score":0,"Single_Score":0,"Multi_Score":0,"UserAnswer":null,"TrueAnswer":null,"Answer_Code":null,"chle":[{"Ques_Id":0,"Answer_Id":226,"Answer_Code":"A","Answer_Content":"我是错误A","Remarks":null},{"Ques_Id":0,"Answer_Id":227,"Answer_Code":"B","Answer_Content":"我是错误B","Remarks":null},{"Ques_Id":0,"Answer_Id":228,"Answer_Code":"C","Answer_Content":"我是错误C","Remarks":null},{"Ques_Id":0,"Answer_Id":229,"Answer_Code":"D","Answer_Content":"我是错误D","Remarks":null},{"Ques_Id":0,"Answer_Id":230,"Answer_Code":"E","Answer_Content":"我是错误E","Remarks":null},{"Ques_Id":0,"Answer_Id":231,"Answer_Code":"F","Answer_Content":"我是错误F","Remarks":null},{"Ques_Id":0,"Answer_Id":232,"Answer_Code":"G","Answer_Content":"我是错误G","Remarks":null}]}]
     * Score : null
     */

    private Object addressid;
    private Object Eid;
    private int Paper_Id;
    private String Paper_Title;
    private Object DXList;
    private Object PDList;
    private Object DuoXList;
    private Object Score;
    private List<Quesition> QuesXList;

    public Object getAddressid() {
        return addressid;
    }

    public void setAddressid(Object addressid) {
        this.addressid = addressid;
    }

    public Object getEid() {
        return Eid;
    }

    public void setEid(Object Eid) {
        this.Eid = Eid;
    }

    public int getPaper_Id() {
        return Paper_Id;
    }

    public void setPaper_Id(int Paper_Id) {
        this.Paper_Id = Paper_Id;
    }

    public String getPaper_Title() {
        return Paper_Title;
    }

    public void setPaper_Title(String Paper_Title) {
        this.Paper_Title = Paper_Title;
    }

    public Object getDXList() {
        return DXList;
    }

    public void setDXList(Object DXList) {
        this.DXList = DXList;
    }

    public Object getPDList() {
        return PDList;
    }

    public void setPDList(Object PDList) {
        this.PDList = PDList;
    }

    public Object getDuoXList() {
        return DuoXList;
    }

    public void setDuoXList(Object DuoXList) {
        this.DuoXList = DuoXList;
    }

    public Object getScore() {
        return Score;
    }

    public void setScore(Object Score) {
        this.Score = Score;
    }

    public List<Quesition> getQuestionList() {
        return QuesXList;
    }

    public void setQuestionList(List<Quesition> QuesXList) {
        this.QuesXList = QuesXList;
    }

    public static class Quesition {
        /**
         * 10 :判断
         * 20 ：单选
         * 30 :多选
         * Ques_Type : 单选题
         * Ques_Code : 20
         * Ques_Id : 46
         * Ques_Name : 我是单选
         * Answer : null
         * Lib_Id : 0
         * Judge_Score : 0
         * Single_Score : 0
         * Multi_Score : 0
         * UserAnswer : null
         * TrueAnswer : null
         * Answer_Code : null
         * "IFPicture"：0，//该试题是否存在图片：0代表不存在，1代表存在
         * "Address":"http://www.baidu.com" //图片地址
         * chle : [{"Ques_Id":0,"Answer_Id":233,"Answer_Code":"A","Answer_Content":"aaaaa","Remarks":null},{"Ques_Id":0,"Answer_Id":234,"Answer_Code":"B","Answer_Content":"bbbbbb","Remarks":null},{"Ques_Id":0,"Answer_Id":235,"Answer_Code":"C","Answer_Content":"vvvvvv","Remarks":null},{"Ques_Id":0,"Answer_Id":236,"Answer_Code":"D","Answer_Content":"dddddd","Remarks":null}]
         */

        private String Ques_Type;
        private String Ques_Code;
        private int Ques_Id;
        private String Ques_Name;
        private Object Answer;
        private int Lib_Id;
        private int Judge_Score;
        private int Single_Score;
        private int Multi_Score;
        private Object UserAnswer;
        private Object TrueAnswer;
        private Object Answer_Code;
        private String IFPicture;
        private String Address;
        //是否解答
        public int que_state;
        /**
         * 某种题型的总量
         */
        private String QuesCount;
        /**
         * 某种题型的总分数
         */
        private String QuesFraction;

        private List<Quesition.Answer> chle;

        public String getQues_Type() {
            return Ques_Type;
        }

        public void setQues_Type(String Ques_Type) {
            this.Ques_Type = Ques_Type;
        }

        public String getQues_Code() {
            return Ques_Code;
        }

        public void setQues_Code(String Ques_Code) {
            this.Ques_Code = Ques_Code;
        }

        public int getQues_Id() {
            return Ques_Id;
        }

        public void setQues_Id(int Ques_Id) {
            this.Ques_Id = Ques_Id;
        }

        public String getQues_Name() {
            return Ques_Name;
        }

        public void setQues_Name(String Ques_Name) {
            this.Ques_Name = Ques_Name;
        }

        public Object getAnswer() {
            return Answer;
        }

        public void setAnswer(Object Answer) {
            this.Answer = Answer;
        }

        public int getLib_Id() {
            return Lib_Id;
        }

        public void setLib_Id(int Lib_Id) {
            this.Lib_Id = Lib_Id;
        }

        public int getJudge_Score() {
            return Judge_Score;
        }

        public void setJudge_Score(int Judge_Score) {
            this.Judge_Score = Judge_Score;
        }

        public int getSingle_Score() {
            return Single_Score;
        }

        public void setSingle_Score(int Single_Score) {
            this.Single_Score = Single_Score;
        }

        public int getMulti_Score() {
            return Multi_Score;
        }

        public void setMulti_Score(int Multi_Score) {
            this.Multi_Score = Multi_Score;
        }

        public Object getUserAnswer() {
            return UserAnswer;
        }

        public void setUserAnswer(Object UserAnswer) {
            this.UserAnswer = UserAnswer;
        }

        public Object getTrueAnswer() {
            return TrueAnswer;
        }

        public void setTrueAnswer(Object TrueAnswer) {
            this.TrueAnswer = TrueAnswer;
        }

        public Object getAnswer_Code() {
            return Answer_Code;
        }

        public void setAnswer_Code(Object Answer_Code) {
            this.Answer_Code = Answer_Code;
        }

        public List<Quesition.Answer> getChle() {
            return chle;
        }

        public String getQuesCount() {
            return QuesCount;
        }

        public void setQuesCount(String quesCount) {
            QuesCount = quesCount;
        }

        public String getQuesFraction() {
            return QuesFraction;
        }

        public void setQuesFraction(String quesFraction) {
            QuesFraction = quesFraction;
        }

        public String getIFPicture() {
            return IFPicture;
        }

        public void setIFPicture(String IFPicture) {
            this.IFPicture = IFPicture;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public void setChle(List<Quesition.Answer> chle) {
            this.chle = chle;
        }

        public static class Answer {
            /**
             * Ques_Id : 0
             * Answer_Id : 233
             * Answer_Code : A
             * Answer_Content : aaaaa
             * Remarks : null
             * "IFPicture"：0，//该试题是否存在图片：0代表不存在，1代表存在
             * "Address":"http://www.baidu.com" //图片地址
             */

            private int Ques_Id;
            private int Answer_Id;
            private String Answer_Code;
            private String Answer_Content;
            private Object Remarks;
            private String IFPicture;
            private String Address;
            //答案是否被解答 1:解答 0：未解答
            public int ans_state;

            public int getQues_Id() {
                return Ques_Id;
            }

            public void setQues_Id(int Ques_Id) {
                this.Ques_Id = Ques_Id;
            }

            public int getAnswer_Id() {
                return Answer_Id;
            }

            public void setAnswer_Id(int Answer_Id) {
                this.Answer_Id = Answer_Id;
            }

            public String getAnswer_Code() {
                return Answer_Code;
            }

            public void setAnswer_Code(String Answer_Code) {
                this.Answer_Code = Answer_Code;
            }

            public String getAnswer_Content() {
                return Answer_Content;
            }

            public void setAnswer_Content(String Answer_Content) {
                this.Answer_Content = Answer_Content;
            }

            public Object getRemarks() {
                return Remarks;
            }

            public void setRemarks(Object Remarks) {
                this.Remarks = Remarks;
            }

            public String getIFPicture() {
                return IFPicture;
            }

            public void setIFPicture(String IFPicture) {
                this.IFPicture = IFPicture;
            }

            public String getAddress() {
                return Address;
            }

            public void setAddress(String address) {
                Address = address;
            }
        }
    }
}
