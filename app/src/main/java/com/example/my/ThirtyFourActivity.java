package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.my.adapter.ExamPaperNoAnswerViewPagerAdapter;
import com.example.my.base.BaseActivity;
import com.example.my.bean.Page;
import com.example.my.fragment.ExamPaperNoAnswerDetailFragment;
import com.example.my.fragment.ExamUsedGuideDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  wsl
 * on 2019/6/18 12:52
 * 调查问卷
 */
public class ThirtyFourActivity extends BaseActivity {
    private MyHandler myHandler;
    private ViewPager viewPagerExam;
    private TextView tvCurPage;
    private TextView tvSubmit;
    private int lastPositionOffsetPixels = 1;
    private List<Page.Quesition> questionList;
    private int paperId;
    private int curSelPage;
    private int quesCount;
    private List<Fragment> fragmentList = new ArrayList<>();
    private boolean firstIntoPaper;
    private boolean examCompleted = false;
    //    private String json = "[{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":205,\"Ques_Name\":\"下列选项中哪种电气操作会引发住宅火灾（ ）？\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924109517icon_tu1.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"电线老化，接线错误\",\"Answer_Id\":1193,\"IFPicture\":\"1\",\"Ques_Id\":205,\"ans_state\":0},{\"Address\":\"baidu.com/20190924104151icon_tu2.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"不乱接乱拉电线，不超负荷用电\",\"Answer_Id\":1194,\"IFPicture\":\"1\",\"Ques_Id\":205,\"ans_state\":0},{\"Address\":\"baidu.com/20190924100702icon_tu3.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"大功率电器用专用线路\",\"Answer_Id\":1195,\"IFPicture\":\"1\",\"Ques_Id\":205,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":206,\"Ques_Name\":\"最常发生电线短路的位置是（）。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924101727icon_tu4.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"电灯灯口\",\"Answer_Id\":1270,\"IFPicture\":\"1\",\"Ques_Id\":206,\"ans_state\":0},{\"Address\":\"baidu.com/20190924105232icon_tu5.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"破损老化电线\",\"Answer_Id\":1271,\"IFPicture\":\"1\",\"Ques_Id\":206,\"ans_state\":0},{\"Address\":\"baidu.com/20190924113448icon_tu302.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"插座及插头接触部位\",\"Answer_Id\":1272,\"IFPicture\":\"1\",\"Ques_Id\":206,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":207,\"Ques_Name\":\"对夏季使用频繁的电器为防止其引起火灾，应采取用以下哪种措施（）。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/201909250948501e6deea142f15dda72a7ad1135c2e46.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"拔掉电器插头\",\"Answer_Id\":1354,\"IFPicture\":\"1\",\"Ques_Id\":207,\"ans_state\":0},{\"Address\":\"baidu.com/20190924113933icon_tu8.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"多擦拭电器外部\",\"Answer_Id\":1355,\"IFPicture\":\"1\",\"Ques_Id\":207,\"ans_state\":0},{\"Address\":\"baidu.com/20190924119843icon_tu9.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"加装触电保安器\",\"Answer_Id\":1356,\"IFPicture\":\"1\",\"Ques_Id\":207,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":212,\"Ques_Name\":\"检查天燃气用具是否漏气时，通常采用（）来寻找漏气点。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924141717icon_tu10.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"划火柴\",\"Answer_Id\":1396,\"IFPicture\":\"1\",\"Ques_Id\":212,\"ans_state\":0},{\"Address\":\"baidu.com/20190924141323icon_tu11.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"肥皂水\",\"Answer_Id\":1397,\"IFPicture\":\"1\",\"Ques_Id\":212,\"ans_state\":0},{\"Address\":\"baidu.com/20190924144145icon_tu12.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"矿泉水\",\"Answer_Id\":1398,\"IFPicture\":\"1\",\"Ques_Id\":212,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":213,\"Ques_Name\":\"家用天燃气管多长时间更换一次，以下正确的是（）。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"A\",\"Answer_Content\":\"12个月\",\"Answer_Id\":1402,\"IFPicture\":\"0\",\"Ques_Id\":213,\"ans_state\":0},{\"Answer_Code\":\"B\",\"Answer_Content\":\"18个月\",\"Answer_Id\":1403,\"IFPicture\":\"0\",\"Ques_Id\":213,\"ans_state\":0},{\"Answer_Code\":\"C\",\"Answer_Content\":\"24个月\",\"Answer_Id\":1404,\"IFPicture\":\"0\",\"Ques_Id\":213,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":214,\"Ques_Name\":\"家庭中使用天燃气设施和用具时，正确的做法是（）\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924177618faf659535694e57e8490877e7f3a922.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"自行更换、拆改燃气的管道\",\"Answer_Id\":1347,\"IFPicture\":\"1\",\"Ques_Id\":214,\"ans_state\":0},{\"Address\":\"baidu.com/20190924143827icon_tu14.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"燃气使用完毕后不关闭总阀门\",\"Answer_Id\":1348,\"IFPicture\":\"1\",\"Ques_Id\":214,\"ans_state\":0},{\"Address\":\"baidu.com/20190924144535icon_tu15.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"经常检查燃气灶具及管道，不擅自安装、拆改\",\"Answer_Id\":1349,\"IFPicture\":\"1\",\"Ques_Id\":214,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":220,\"Ques_Name\":\"烟蒂头的中心温度可达（）摄氏度？\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"A\",\"Answer_Content\":\"300～500\",\"Answer_Id\":1399,\"IFPicture\":\"0\",\"Ques_Id\":220,\"ans_state\":0},{\"Answer_Code\":\"B\",\"Answer_Content\":\"500～700\",\"Answer_Id\":1400,\"IFPicture\":\"0\",\"Ques_Id\":220,\"ans_state\":0},{\"Answer_Code\":\"C\",\"Answer_Content\":\"700～800\",\"Answer_Id\":1401,\"IFPicture\":\"0\",\"Ques_Id\":220,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":223,\"Ques_Name\":\"家庭装修未经（）的同意，不能随意挪动燃气管线，以免引起燃气泄漏，发生火灾或爆炸。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"A\",\"Answer_Content\":\"消防部门\",\"Answer_Id\":1360,\"IFPicture\":\"0\",\"Ques_Id\":223,\"ans_state\":0},{\"Answer_Code\":\"B\",\"Answer_Content\":\"物业部门\",\"Answer_Id\":1361,\"IFPicture\":\"0\",\"Ques_Id\":223,\"ans_state\":0},{\"Answer_Code\":\"C\",\"Answer_Content\":\"燃气部门\",\"Answer_Id\":1362,\"IFPicture\":\"0\",\"Ques_Id\":223,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":225,\"Ques_Name\":\"如果楼层已着火燃烧，火势并不十分猛烈时可以披上（），从楼上快速冲下。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924144512icon_tu19.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"干的衣被\",\"Answer_Id\":1381,\"IFPicture\":\"1\",\"Ques_Id\":225,\"ans_state\":0},{\"Address\":\"baidu.com/20190924145420icon_tu20.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"用水浸湿的衣物\",\"Answer_Id\":1382,\"IFPicture\":\"1\",\"Ques_Id\":225,\"ans_state\":0},{\"Address\":\"baidu.com/20190924145835icon_tu21.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"塑料或麻的制品\",\"Answer_Id\":1383,\"IFPicture\":\"1\",\"Ques_Id\":225,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":226,\"Ques_Name\":\"发现消防通道被堵塞或封闭时个人采取最正确的行动是：（）\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924140829icon_tu22.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"搬到不妨碍自己的位置\",\"Answer_Id\":1342,\"IFPicture\":\"1\",\"Ques_Id\":226,\"ans_state\":0},{\"Address\":\"baidu.com/20190924148038icon_tu23.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"向防火监督机构举报\",\"Answer_Id\":1343,\"IFPicture\":\"1\",\"Ques_Id\":226,\"ans_state\":0},{\"Address\":\"baidu.com/20190924146544icon_tu24.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"不予理睬\",\"Answer_Id\":1344,\"IFPicture\":\"1\",\"Ques_Id\":226,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":208,\"Ques_Name\":\"居民住宅中如何安全用电，以下做法正确的是（）?\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924108913icon_tu60.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"安装合格的空气开关或漏电保护装置\",\"Answer_Id\":1266,\"IFPicture\":\"1\",\"Ques_Id\":208,\"ans_state\":0},{\"Address\":\"baidu.com/20190924116600icon_tu61.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"正确使用电源线\",\"Answer_Id\":1267,\"IFPicture\":\"1\",\"Ques_Id\":208,\"ans_state\":0},{\"Address\":\"baidu.com/20190924100237icon_tu62.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"合理地布置电线\",\"Answer_Id\":1268,\"IFPicture\":\"1\",\"Ques_Id\":208,\"ans_state\":0},{\"Address\":\"baidu.com/20190924109945icon_tu63.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"正确使用家用电器\",\"Answer_Id\":1269,\"IFPicture\":\"1\",\"Ques_Id\":208,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":209,\"Ques_Name\":\"厨房用电要注意些什么（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924183225icon_tu25.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"湿手不得接触电器和电器装置\",\"Answer_Id\":1363,\"IFPicture\":\"1\",\"Ques_Id\":209,\"ans_state\":0},{\"Address\":\"baidu.com/20190924182034icon_tu26.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"灯头应使用螺口式，并加装安全罩\",\"Answer_Id\":1364,\"IFPicture\":\"1\",\"Ques_Id\":209,\"ans_state\":0},{\"Address\":\"baidu.com/20190924183646icon_tu27.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"电器用完后关掉开关\",\"Answer_Id\":1365,\"IFPicture\":\"1\",\"Ques_Id\":209,\"ans_state\":0},{\"Address\":\"baidu.com/20190924189301icon_tu28.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"家用照明电路接用电炉\",\"Answer_Id\":1366,\"IFPicture\":\"1\",\"Ques_Id\":209,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":210,\"Ques_Name\":\"家用电器或线路着火应该怎样扑救（）?\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924106847icon_tu29.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"立即切断电源或拉下总闸\",\"Answer_Id\":1415,\"IFPicture\":\"1\",\"Ques_Id\":210,\"ans_state\":0},{\"Address\":\"baidu.com/20190924114259icon_tu30.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"用湿棉被等覆盖物封闭窒息灭火\",\"Answer_Id\":1416,\"IFPicture\":\"1\",\"Ques_Id\":210,\"ans_state\":0},{\"Address\":\"baidu.com/20190924108630icon_tu301.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"家用电器发生火灾后未经修理不得接通电源使用\",\"Answer_Id\":1417,\"IFPicture\":\"1\",\"Ques_Id\":210,\"ans_state\":0},{\"Address\":\"baidu.com/201909271653467e96ca23d72a3c14d18a70b79cfc998.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"直接用水或泡沫灭火剂扑灭电器火灾\",\"Answer_Id\":1418,\"IFPicture\":\"1\",\"Ques_Id\":210,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":215,\"Ques_Name\":\"造成燃气火灾的原因以下说法正确的是（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924117129icon_tu6.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"室内阀门关闭不严，阀杆、丝扣损坏失灵\",\"Answer_Id\":1286,\"IFPicture\":\"1\",\"Ques_Id\":215,\"ans_state\":0},{\"Address\":\"baidu.com/20190924118037icon_tu13.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"阀门不符合安全质量要求\",\"Answer_Id\":1287,\"IFPicture\":\"1\",\"Ques_Id\":215,\"ans_state\":0},{\"Address\":\"baidu.com/20190924117753icon_tu34.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"误开阀门，使燃气逸出，遇到明火燃烧或爆炸\",\"Answer_Id\":1288,\"IFPicture\":\"1\",\"Ques_Id\":215,\"ans_state\":0},{\"Address\":\"baidu.com/20190924115103icon_tu35.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"灶台前要时时刻刻有人\",\"Answer_Id\":1289,\"IFPicture\":\"1\",\"Ques_Id\":215,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":216,\"Ques_Name\":\"家庭做饭热油时，油锅过热起火后以下做法正确的是（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924140617icon_tu36.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"应关闭炉灶燃气阀门\",\"Answer_Id\":1435,\"IFPicture\":\"1\",\"Ques_Id\":216,\"ans_state\":0},{\"Address\":\"baidu.com/20190924149327icon_tu37.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"直接盖上锅盖或用湿抹布覆盖，令火窒息\",\"Answer_Id\":1436,\"IFPicture\":\"1\",\"Ques_Id\":216,\"ans_state\":0},{\"Address\":\"baidu.com/20190924149338icon_tu38.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"油锅着火后泼水灭火\",\"Answer_Id\":1437,\"IFPicture\":\"1\",\"Ques_Id\":216,\"ans_state\":0},{\"Address\":\"baidu.com/201909271650475b3bf7a5496d9e25bab82485566a38b.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"打开油烟机将浓烟抽走\",\"Answer_Id\":1438,\"IFPicture\":\"1\",\"Ques_Id\":216,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":217,\"Ques_Name\":\"在火灾初期阶段如何采取有力的措施灭火（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924148808icon_tu48.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"拨打“119”火警电话，讲清路线、门牌号后，派人在路口等待消防车外\",\"Answer_Id\":1427,\"IFPicture\":\"1\",\"Ques_Id\":217,\"ans_state\":0},{\"Address\":\"baidu.com/20190924146718icon_tu49.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"用灭火器扑灭火苗，或用湿毛毯、湿棉被罩住火焰，然后将火扑灭\",\"Answer_Id\":1428,\"IFPicture\":\"1\",\"Ques_Id\":217,\"ans_state\":0},{\"Address\":\"baidu.com/20190924144733icon_tu50.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"液化气灶着火后要先关闭阀门，再往上浇水扑灭\",\"Answer_Id\":1429,\"IFPicture\":\"1\",\"Ques_Id\":217,\"ans_state\":0},{\"Address\":\"baidu.com/20190927168610266759601c00de1fb8fb4d6591c81c2.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"封闭的房间着火后要立刻打开门窗通风\",\"Answer_Id\":1430,\"IFPicture\":\"1\",\"Ques_Id\":217,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":222,\"Ques_Name\":\"点蚊香要注意什么，以下做法正确的是（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924117306icon_tu52.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"蚊香要放在床边驱蚊效果更好。\",\"Answer_Id\":1282,\"IFPicture\":\"1\",\"Ques_Id\":222,\"ans_state\":0},{\"Address\":\"baidu.com/20190924113617icon_tu53.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"点蚊香时，一定要把蚊香固定在专用的铁架上。\",\"Answer_Id\":1283,\"IFPicture\":\"1\",\"Ques_Id\":222,\"ans_state\":0},{\"Address\":\"baidu.com/20190924113835icon_tu54.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"蚊香应远离窗帘、蚊帐、床单、衣服等可燃物。\",\"Answer_Id\":1284,\"IFPicture\":\"1\",\"Ques_Id\":222,\"ans_state\":0},{\"Address\":\"baidu.com/20190924111647icon_tu55.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"点燃蚊香的时候人员不可以离开。\",\"Answer_Id\":1285,\"IFPicture\":\"1\",\"Ques_Id\":222,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":224,\"Ques_Name\":\"儿童在日常生活中需要从哪些方面注意消防安全问题（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190925098006icon_tu56.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"要从小教育孩子不要玩火\",\"Answer_Id\":1431,\"IFPicture\":\"1\",\"Ques_Id\":224,\"ans_state\":0},{\"Address\":\"baidu.com/20190927165808d2f018220159672e7281f75555b96da.jpg\",\"Answer_Code\":\"B\",\"Answer_Content\":\"火柴、打火机、蜡烛等放在孩子拿不到的地方\",\"Answer_Id\":1432,\"IFPicture\":\"1\",\"Ques_Id\":224,\"ans_state\":0},{\"Address\":\"baidu.com/20190926168526icon_tu59.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"教育孩子不能乱动和拆卸电器\",\"Answer_Id\":1433,\"IFPicture\":\"1\",\"Ques_Id\":224,\"ans_state\":0},{\"Address\":\"baidu.com/20190926162358icon_tu58.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"教小孩煮饭锻炼其自立能力\",\"Answer_Id\":1434,\"IFPicture\":\"1\",\"Ques_Id\":224,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":227,\"Ques_Name\":\"家庭宜配备哪些消防器材：（）\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924147224icon_tu40.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"手提式灭火器\",\"Answer_Id\":1419,\"IFPicture\":\"1\",\"Ques_Id\":227,\"ans_state\":0},{\"Address\":\"baidu.com/20190924140249icon_tu41.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"智能物联网可燃气体探测器\",\"Answer_Id\":1420,\"IFPicture\":\"1\",\"Ques_Id\":227,\"ans_state\":0},{\"Address\":\"baidu.com/20190924143755icon_tu42.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"智能物联网感烟火灾探测器\",\"Answer_Id\":1421,\"IFPicture\":\"1\",\"Ques_Id\":227,\"ans_state\":0},{\"Address\":\"baidu.com/20190924147443icon_tu43.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"简易防烟面罩\",\"Answer_Id\":1422,\"IFPicture\":\"1\",\"Ques_Id\":227,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":228,\"Ques_Name\":\"家庭成员应时刻做好以下哪项消防安全的检查工作（）\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924143313icon_tu44.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"经常检查家中的电气线路是否有破损\",\"Answer_Id\":1367,\"IFPicture\":\"1\",\"Ques_Id\":228,\"ans_state\":0},{\"Address\":\"baidu.com/20190924147259icon_tu45.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"家中电器是否属超负荷使用等\",\"Answer_Id\":1368,\"IFPicture\":\"1\",\"Ques_Id\":228,\"ans_state\":0},{\"Address\":\"baidu.com/20190925098908icon_tu46.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"经常对液化气灶、导气软管、气瓶作定期保养\",\"Answer_Id\":1369,\"IFPicture\":\"1\",\"Ques_Id\":228,\"ans_state\":0},{\"Address\":\"baidu.com/20190924147035icon_tu47.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"各种电器的插座应远离火源\",\"Answer_Id\":1370,\"IFPicture\":\"1\",\"Ques_Id\":228,\"ans_state\":0}],\"que_state\":0},{\"Address\":\"http://wjdc.test.119xiehui.com/images/20190924113121icon_tu64.png\",\"IFPicture\":\"1\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"10\",\"QuesFraction\":\"10\",\"Ques_Code\":\"10\",\"Ques_Id\":211,\"Ques_Name\":\"电热水器要时常检查其自动调节装置是否损坏。\",\"Ques_Type\":\"判断题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"对\",\"Answer_Content\":\"对\",\"Answer_Id\":1276,\"Ques_Id\":211,\"ans_state\":0},{\"Answer_Code\":\"错\",\"Answer_Content\":\"错\",\"Answer_Id\":1277,\"Ques_Id\":211,\"ans_state\":0}],\"que_state\":0},{\"Address\":\"http://wjdc.test.119xiehui.com/images/20190924110457icon_tu65.png\",\"IFPicture\":\"1\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"10\",\"QuesFraction\":\"10\",\"Ques_Code\":\"10\",\"Ques_Id\":218,\"Ques_Name\":\"煤气火焰正常呈淡蓝色，如发现呈红色，即表示不完全燃烧现象。\",\"Ques_Type\":\"判断题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"对\",\"Answer_Content\":\"对\",\"Answer_Id\":1375,\"Ques_Id\":218,\"ans_state\":0},{\"Answer_Code\":\"错\",\"Answer_Content\":\"错\",\"Answer_Id\":1376,\"Ques_Id\":218,\"ans_state\":0}],\"que_state\":0},{\"Address\":\"http://wjdc.test.119xiehui.com/images/20190924113724icon_tu66.png\",\"IFPicture\":\"1\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"10\",\"QuesFraction\":\"10\",\"Ques_Code\":\"10\",\"Ques_Id\":219,\"Ques_Name\":\"使用天然气时如发现有类似坏鸡蛋的臭味，要检查是否存在漏气。\",\"Ques_Type\":\"判断题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"对\",\"Answer_Content\":\"对\",\"Answer_Id\":1413,\"Ques_Id\":219,\"ans_state\":0},{\"Answer_Code\":\"错\",\"Answer_Content\":\"错\",\"Answer_Id\":1414,\"Ques_Id\":219,\"ans_state\":0}],\"que_state\":0},{\"Address\":\"http://wjdc.test.119xiehui.com/images/20190924143614icon_tu67.png\",\"IFPicture\":\"1\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"10\",\"QuesFraction\":\"10\",\"Ques_Code\":\"10\",\"Ques_Id\":229,\"Ques_Name\":\"安全出口处要设明显的标志，疏散通道必须保持畅通严。\",\"Ques_Type\":\"判断题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"对\",\"Answer_Content\":\"对\",\"Answer_Id\":1345,\"Ques_Id\":229,\"ans_state\":0},{\"Answer_Code\":\"错\",\"Answer_Content\":\"错\",\"Answer_Id\":1346,\"Ques_Id\":229,\"ans_state\":0}],\"que_state\":0},{\"Address\":\"http://wjdc.test.119xiehui.com/images/20190924142114icon_tu68.png\",\"IFPicture\":\"1\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"10\",\"QuesFraction\":\"10\",\"Ques_Code\":\"10\",\"Ques_Id\":230,\"Ques_Name\":\"防火门应保持打开状态，楼道内消防设施应配备齐全。\",\"Ques_Type\":\"判断题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"对\",\"Answer_Content\":\"对\",\"Answer_Id\":1340,\"Ques_Id\":230,\"ans_state\":0},{\"Answer_Code\":\"错\",\"Answer_Content\":\"错\",\"Answer_Id\":1341,\"Ques_Id\":230,\"ans_state\":0}],\"que_state\":0}]";
    private String json = "[{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":205,\"Ques_Name\":\"下列选项中哪种电气操作会引发住宅火灾（ ）？\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924109517icon_tu1.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"电线老化，接线错误\",\"Answer_Id\":1193,\"IFPicture\":\"1\",\"Ques_Id\":205,\"ans_state\":0},{\"Address\":\"baidu.com/20190924104151icon_tu2.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"不乱接乱拉电线，不超负荷用电\",\"Answer_Id\":1194,\"IFPicture\":\"1\",\"Ques_Id\":205,\"ans_state\":0},{\"Address\":\"baidu.com/20190924100702icon_tu3.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"大功率电器用专用线路\",\"Answer_Id\":1195,\"IFPicture\":\"1\",\"Ques_Id\":205,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":206,\"Ques_Name\":\"最常发生电线短路的位置是（）。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924101727icon_tu4.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"电灯灯口\",\"Answer_Id\":1270,\"IFPicture\":\"1\",\"Ques_Id\":206,\"ans_state\":0},{\"Address\":\"baidu.com/20190924105232icon_tu5.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"破损老化电线\",\"Answer_Id\":1271,\"IFPicture\":\"1\",\"Ques_Id\":206,\"ans_state\":0},{\"Address\":\"baidu.com/20190924113448icon_tu302.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"插座及插头接触部位\",\"Answer_Id\":1272,\"IFPicture\":\"1\",\"Ques_Id\":206,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":207,\"Ques_Name\":\"对夏季使用频繁的电器为防止其引起火灾，应采取用以下哪种措施（）。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/201909250948501e6deea142f15dda72a7ad1135c2e46.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"拔掉电器插头\",\"Answer_Id\":1354,\"IFPicture\":\"1\",\"Ques_Id\":207,\"ans_state\":0},{\"Address\":\"baidu.com/20190924113933icon_tu8.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"多擦拭电器外部\",\"Answer_Id\":1355,\"IFPicture\":\"1\",\"Ques_Id\":207,\"ans_state\":0},{\"Address\":\"baidu.com/20190924119843icon_tu9.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"加装触电保安器\",\"Answer_Id\":1356,\"IFPicture\":\"1\",\"Ques_Id\":207,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":212,\"Ques_Name\":\"检查天燃气用具是否漏气时，通常采用（）来寻找漏气点。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924141717icon_tu10.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"划火柴\",\"Answer_Id\":1396,\"IFPicture\":\"1\",\"Ques_Id\":212,\"ans_state\":0},{\"Address\":\"baidu.com/20190924141323icon_tu11.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"肥皂水\",\"Answer_Id\":1397,\"IFPicture\":\"1\",\"Ques_Id\":212,\"ans_state\":0},{\"Address\":\"baidu.com/20190924144145icon_tu12.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"矿泉水\",\"Answer_Id\":1398,\"IFPicture\":\"1\",\"Ques_Id\":212,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":213,\"Ques_Name\":\"家用天燃气管多长时间更换一次，以下正确的是（）。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"A\",\"Answer_Content\":\"12个月\",\"Answer_Id\":1402,\"IFPicture\":\"0\",\"Ques_Id\":213,\"ans_state\":0},{\"Answer_Code\":\"B\",\"Answer_Content\":\"18个月\",\"Answer_Id\":1403,\"IFPicture\":\"0\",\"Ques_Id\":213,\"ans_state\":0},{\"Answer_Code\":\"C\",\"Answer_Content\":\"24个月\",\"Answer_Id\":1404,\"IFPicture\":\"0\",\"Ques_Id\":213,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":214,\"Ques_Name\":\"家庭中使用天燃气设施和用具时，正确的做法是（）\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924177618faf659535694e57e8490877e7f3a922.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"自行更换、拆改燃气的管道\",\"Answer_Id\":1347,\"IFPicture\":\"1\",\"Ques_Id\":214,\"ans_state\":0},{\"Address\":\"baidu.com/20190924143827icon_tu14.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"燃气使用完毕后不关闭总阀门\",\"Answer_Id\":1348,\"IFPicture\":\"1\",\"Ques_Id\":214,\"ans_state\":0},{\"Address\":\"baidu.com/20190924144535icon_tu15.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"经常检查燃气灶具及管道，不擅自安装、拆改\",\"Answer_Id\":1349,\"IFPicture\":\"1\",\"Ques_Id\":214,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":220,\"Ques_Name\":\"烟蒂头的中心温度可达（）摄氏度？\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"A\",\"Answer_Content\":\"300～500\",\"Answer_Id\":1399,\"IFPicture\":\"0\",\"Ques_Id\":220,\"ans_state\":0},{\"Answer_Code\":\"B\",\"Answer_Content\":\"500～700\",\"Answer_Id\":1400,\"IFPicture\":\"0\",\"Ques_Id\":220,\"ans_state\":0},{\"Answer_Code\":\"C\",\"Answer_Content\":\"700～800\",\"Answer_Id\":1401,\"IFPicture\":\"0\",\"Ques_Id\":220,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":223,\"Ques_Name\":\"家庭装修未经（）的同意，不能随意挪动燃气管线，以免引起燃气泄漏，发生火灾或爆炸。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Answer_Code\":\"A\",\"Answer_Content\":\"消防部门\",\"Answer_Id\":1360,\"IFPicture\":\"0\",\"Ques_Id\":223,\"ans_state\":0},{\"Answer_Code\":\"B\",\"Answer_Content\":\"物业部门\",\"Answer_Id\":1361,\"IFPicture\":\"0\",\"Ques_Id\":223,\"ans_state\":0},{\"Answer_Code\":\"C\",\"Answer_Content\":\"燃气部门\",\"Answer_Id\":1362,\"IFPicture\":\"0\",\"Ques_Id\":223,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":225,\"Ques_Name\":\"如果楼层已着火燃烧，火势并不十分猛烈时可以披上（），从楼上快速冲下。\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924144512icon_tu19.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"干的衣被\",\"Answer_Id\":1381,\"IFPicture\":\"1\",\"Ques_Id\":225,\"ans_state\":0},{\"Address\":\"baidu.com/20190924145420icon_tu20.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"用水浸湿的衣物\",\"Answer_Id\":1382,\"IFPicture\":\"1\",\"Ques_Id\":225,\"ans_state\":0},{\"Address\":\"baidu.com/20190924145835icon_tu21.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"塑料或麻的制品\",\"Answer_Id\":1383,\"IFPicture\":\"1\",\"Ques_Id\":225,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"40\",\"Ques_Code\":\"20\",\"Ques_Id\":226,\"Ques_Name\":\"发现消防通道被堵塞或封闭时个人采取最正确的行动是：（）\",\"Ques_Type\":\"单选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924140829icon_tu22.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"搬到不妨碍自己的位置\",\"Answer_Id\":1342,\"IFPicture\":\"1\",\"Ques_Id\":226,\"ans_state\":0},{\"Address\":\"baidu.com/20190924148038icon_tu23.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"向防火监督机构举报\",\"Answer_Id\":1343,\"IFPicture\":\"1\",\"Ques_Id\":226,\"ans_state\":0},{\"Address\":\"baidu.com/20190924146544icon_tu24.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"不予理睬\",\"Answer_Id\":1344,\"IFPicture\":\"1\",\"Ques_Id\":226,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":208,\"Ques_Name\":\"居民住宅中如何安全用电，以下做法正确的是（）?\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924108913icon_tu60.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"安装合格的空气开关或漏电保护装置\",\"Answer_Id\":1266,\"IFPicture\":\"1\",\"Ques_Id\":208,\"ans_state\":0},{\"Address\":\"baidu.com/20190924116600icon_tu61.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"正确使用电源线\",\"Answer_Id\":1267,\"IFPicture\":\"1\",\"Ques_Id\":208,\"ans_state\":0},{\"Address\":\"baidu.com/20190924100237icon_tu62.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"合理地布置电线\",\"Answer_Id\":1268,\"IFPicture\":\"1\",\"Ques_Id\":208,\"ans_state\":0},{\"Address\":\"baidu.com/20190924109945icon_tu63.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"正确使用家用电器\",\"Answer_Id\":1269,\"IFPicture\":\"1\",\"Ques_Id\":208,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":209,\"Ques_Name\":\"厨房用电要注意些什么（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924183225icon_tu25.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"湿手不得接触电器和电器装置\",\"Answer_Id\":1363,\"IFPicture\":\"1\",\"Ques_Id\":209,\"ans_state\":0},{\"Address\":\"baidu.com/20190924182034icon_tu26.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"灯头应使用螺口式，并加装安全罩\",\"Answer_Id\":1364,\"IFPicture\":\"1\",\"Ques_Id\":209,\"ans_state\":0},{\"Address\":\"baidu.com/20190924183646icon_tu27.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"电器用完后关掉开关\",\"Answer_Id\":1365,\"IFPicture\":\"1\",\"Ques_Id\":209,\"ans_state\":0},{\"Address\":\"baidu.com/20190924189301icon_tu28.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"家用照明电路接用电炉\",\"Answer_Id\":1366,\"IFPicture\":\"1\",\"Ques_Id\":209,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":210,\"Ques_Name\":\"家用电器或线路着火应该怎样扑救（）?\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924106847icon_tu29.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"立即切断电源或拉下总闸\",\"Answer_Id\":1415,\"IFPicture\":\"1\",\"Ques_Id\":210,\"ans_state\":0},{\"Address\":\"baidu.com/20190924114259icon_tu30.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"用湿棉被等覆盖物封闭窒息灭火\",\"Answer_Id\":1416,\"IFPicture\":\"1\",\"Ques_Id\":210,\"ans_state\":0},{\"Address\":\"baidu.com/20190924108630icon_tu301.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"家用电器发生火灾后未经修理不得接通电源使用\",\"Answer_Id\":1417,\"IFPicture\":\"1\",\"Ques_Id\":210,\"ans_state\":0},{\"Address\":\"baidu.com/201909271653467e96ca23d72a3c14d18a70b79cfc998.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"直接用水或泡沫灭火剂扑灭电器火灾\",\"Answer_Id\":1418,\"IFPicture\":\"1\",\"Ques_Id\":210,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":215,\"Ques_Name\":\"造成燃气火灾的原因以下说法正确的是（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924117129icon_tu6.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"室内阀门关闭不严，阀杆、丝扣损坏失灵\",\"Answer_Id\":1286,\"IFPicture\":\"1\",\"Ques_Id\":215,\"ans_state\":0},{\"Address\":\"baidu.com/20190924118037icon_tu13.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"阀门不符合安全质量要求\",\"Answer_Id\":1287,\"IFPicture\":\"1\",\"Ques_Id\":215,\"ans_state\":0},{\"Address\":\"baidu.com/20190924117753icon_tu34.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"误开阀门，使燃气逸出，遇到明火燃烧或爆炸\",\"Answer_Id\":1288,\"IFPicture\":\"1\",\"Ques_Id\":215,\"ans_state\":0},{\"Address\":\"baidu.com/20190924115103icon_tu35.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"灶台前要时时刻刻有人\",\"Answer_Id\":1289,\"IFPicture\":\"1\",\"Ques_Id\":215,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":216,\"Ques_Name\":\"家庭做饭热油时，油锅过热起火后以下做法正确的是（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924140617icon_tu36.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"应关闭炉灶燃气阀门\",\"Answer_Id\":1435,\"IFPicture\":\"1\",\"Ques_Id\":216,\"ans_state\":0},{\"Address\":\"baidu.com/20190924149327icon_tu37.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"直接盖上锅盖或用湿抹布覆盖，令火窒息\",\"Answer_Id\":1436,\"IFPicture\":\"1\",\"Ques_Id\":216,\"ans_state\":0},{\"Address\":\"baidu.com/20190924149338icon_tu38.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"油锅着火后泼水灭火\",\"Answer_Id\":1437,\"IFPicture\":\"1\",\"Ques_Id\":216,\"ans_state\":0},{\"Address\":\"baidu.com/201909271650475b3bf7a5496d9e25bab82485566a38b.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"打开油烟机将浓烟抽走\",\"Answer_Id\":1438,\"IFPicture\":\"1\",\"Ques_Id\":216,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":217,\"Ques_Name\":\"在火灾初期阶段如何采取有力的措施灭火（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924148808icon_tu48.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"拨打“119”火警电话，讲清路线、门牌号后，派人在路口等待消防车外\",\"Answer_Id\":1427,\"IFPicture\":\"1\",\"Ques_Id\":217,\"ans_state\":0},{\"Address\":\"baidu.com/20190924146718icon_tu49.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"用灭火器扑灭火苗，或用湿毛毯、湿棉被罩住火焰，然后将火扑灭\",\"Answer_Id\":1428,\"IFPicture\":\"1\",\"Ques_Id\":217,\"ans_state\":0},{\"Address\":\"baidu.com/20190924144733icon_tu50.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"液化气灶着火后要先关闭阀门，再往上浇水扑灭\",\"Answer_Id\":1429,\"IFPicture\":\"1\",\"Ques_Id\":217,\"ans_state\":0},{\"Address\":\"baidu.com/20190927168610266759601c00de1fb8fb4d6591c81c2.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"封闭的房间着火后要立刻打开门窗通风\",\"Answer_Id\":1430,\"IFPicture\":\"1\",\"Ques_Id\":217,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":222,\"Ques_Name\":\"点蚊香要注意什么，以下做法正确的是（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924117306icon_tu52.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"蚊香要放在床边驱蚊效果更好。\",\"Answer_Id\":1282,\"IFPicture\":\"1\",\"Ques_Id\":222,\"ans_state\":0},{\"Address\":\"baidu.com/20190924113617icon_tu53.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"点蚊香时，一定要把蚊香固定在专用的铁架上。\",\"Answer_Id\":1283,\"IFPicture\":\"1\",\"Ques_Id\":222,\"ans_state\":0},{\"Address\":\"baidu.com/20190924113835icon_tu54.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"蚊香应远离窗帘、蚊帐、床单、衣服等可燃物。\",\"Answer_Id\":1284,\"IFPicture\":\"1\",\"Ques_Id\":222,\"ans_state\":0},{\"Address\":\"baidu.com/20190924111647icon_tu55.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"点燃蚊香的时候人员不可以离开。\",\"Answer_Id\":1285,\"IFPicture\":\"1\",\"Ques_Id\":222,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":224,\"Ques_Name\":\"儿童在日常生活中需要从哪些方面注意消防安全问题（）？\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190925098006icon_tu56.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"要从小教育孩子不要玩火\",\"Answer_Id\":1431,\"IFPicture\":\"1\",\"Ques_Id\":224,\"ans_state\":0},{\"Address\":\"baidu.com/20190927165808d2f018220159672e7281f75555b96da.jpg\",\"Answer_Code\":\"B\",\"Answer_Content\":\"火柴、打火机、蜡烛等放在孩子拿不到的地方\",\"Answer_Id\":1432,\"IFPicture\":\"1\",\"Ques_Id\":224,\"ans_state\":0},{\"Address\":\"baidu.com/20190926168526icon_tu59.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"教育孩子不能乱动和拆卸电器\",\"Answer_Id\":1433,\"IFPicture\":\"1\",\"Ques_Id\":224,\"ans_state\":0},{\"Address\":\"baidu.com/20190926162358icon_tu58.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"教小孩煮饭锻炼其自立能力\",\"Answer_Id\":1434,\"IFPicture\":\"1\",\"Ques_Id\":224,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":227,\"Ques_Name\":\"家庭宜配备哪些消防器材：（）\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924147224icon_tu40.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"手提式灭火器\",\"Answer_Id\":1419,\"IFPicture\":\"1\",\"Ques_Id\":227,\"ans_state\":0},{\"Address\":\"baidu.com/20190924140249icon_tu41.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"智能物联网可燃气体探测器\",\"Answer_Id\":1420,\"IFPicture\":\"1\",\"Ques_Id\":227,\"ans_state\":0},{\"Address\":\"baidu.com/20190924143755icon_tu42.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"智能物联网感烟火灾探测器\",\"Answer_Id\":1421,\"IFPicture\":\"1\",\"Ques_Id\":227,\"ans_state\":0},{\"Address\":\"baidu.com/20190924147443icon_tu43.png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"简易防烟面罩\",\"Answer_Id\":1422,\"IFPicture\":\"1\",\"Ques_Id\":227,\"ans_state\":0}],\"que_state\":0},{\"IFPicture\":\"0\",\"Judge_Score\":0,\"Lib_Id\":0,\"Multi_Score\":0,\"QuesCount\":\"20\",\"QuesFraction\":\"50\",\"Ques_Code\":\"30\",\"Ques_Id\":228,\"Ques_Name\":\"家庭成员应时刻做好以下哪项消防安全的检查工作（）\",\"Ques_Type\":\"多选题\",\"Single_Score\":0,\"chle\":[{\"Address\":\"baidu.com/20190924143313icon_tu44.png\",\"Answer_Code\":\"A\",\"Answer_Content\":\"经常检查家中的电气线路是否有破损\",\"Answer_Id\":1367,\"IFPicture\":\"1\",\"Ques_Id\":228,\"ans_state\":0},{\"Address\":\"baidu.com/20190924147259icon_tu45.png\",\"Answer_Code\":\"B\",\"Answer_Content\":\"家中电器是否属超负荷使用等\",\"Answer_Id\":1368,\"IFPicture\":\"1\",\"Ques_Id\":228,\"ans_state\":0},{\"Address\":\"baidu.com/20190925098908icon_tu46.png\",\"Answer_Code\":\"C\",\"Answer_Content\":\"经常对液化气灶、导气软管、气瓶作定期保养\",\"Answer_Id\":1369,\"IFPicture\":\"1\",\"Ques_Id\":228,\"ans_state\":0},{\"Address\":\"png\",\"Answer_Code\":\"D\",\"Answer_Content\":\"各种电器的插座应远离火源\",\"Answer_Id\":1370,\"IFPicture\":\"1\",\"Ques_Id\":228,\"ans_state\":0}],\"que_state\":0}]";

    /**
     * 启动activity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThirtyFourActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置是否显示标题栏
        setShowTitle(true);
        //设置是否显示状态栏
        setShowStatusBar(true);
        //是否允许屏幕旋转
        setAllowScreenRoate(true);
        //以上设置一定要在 super.onCreate(savedInstanceState) 方法之前设置
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        //初始化布局
        return R.layout.activity_thirty_four_layout;
    }

    @Override
    protected void initView() {
        viewPagerExam = findViewById(R.id.viewPagerExam);
        ImageView ivBack = findViewById(R.id.ivBack);
        LinearLayout llBack = findViewById(R.id.llBack);
        tvCurPage = findViewById(R.id.tvCurPage);
        tvSubmit = findViewById(R.id.tvSubmit);
        viewPagerExam.setOffscreenPageLimit(3);
        ivBack.setOnClickListener(onSingleClickListener);
        llBack.setOnClickListener(onSingleClickListener);
        tvSubmit.setOnClickListener(onSingleClickListener);
    }

    @Override
    protected void initData() {
        initTitleCurPage(1, 25);

        questionList = fromJsonList(json, Page.Quesition.class);
        quesCount = questionList.size();
        initTitleCurPage(curSelPage + 1, quesCount);

        //设置试题页
        for (int i = 0; i < questionList.size(); i++) {
            fragmentList.add(ExamPaperNoAnswerDetailFragment.newInstance(i));
        }
        viewPagerExam.setAdapter(new ExamPaperNoAnswerViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        viewPagerExam.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //滑动到第一页和最后一页时，进行提示
                if (position == 0 && positionOffsetPixels == 0 && lastPositionOffsetPixels == 0) {
                    showToast("当前是第一页");
                } else if (position == questionList.size() - 1
                        && positionOffsetPixels == 0 && lastPositionOffsetPixels == 0) {
                    showToast("当前是最后一页");
                }
                lastPositionOffsetPixels = positionOffsetPixels;
            }

            @Override
            public void onPageSelected(int position) {
                //当前处于哪一页，从0开始计算
                curSelPage = position;
                initTitleCurPage(curSelPage + 1, quesCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        //启动新手指引动画
        ExamUsedGuideDialogFragment.newInstance().show(getSupportFragmentManager(), "ExamUsedGuideDialog");
    }

    /**
     * 设置标题页码
     *
     * @param curSelPage 当前是第几页
     * @param quesCount  总共多少页
     */
    private void initTitleCurPage(int curSelPage, int quesCount) {
        String curPageHtmlStr = "<font color=\"#111111\">" + curSelPage + "</font>" + "<font color=\"#666666\">/</font>"
                + "<font color=\"#ff0000\">" + quesCount + "</font>";
        tvCurPage.setText(Html.fromHtml(curPageHtmlStr));
    }

    /**
     * 获取当前viewpager的页码
     *
     * @return 当前页的位置
     */
    public int getCurrentPagerIdx() {
        return curSelPage;
    }

    /**
     * 获取题目列表
     *
     * @return List<Page.Quesition>
     */
    public List<Page.Quesition> getQuestionList() {
        return questionList;
    }

    /**
     * 处理答案并提交
     */
    private void handleAnswerAndSubmit() {
        String answer = getSubmitAnswer();
        if (!TextUtils.isEmpty(answer) && answer.contains("001")) {
            showToast("您未答完全部题目");
            if (answer.contains("+")) {
                String[] split = answer.split("\\+");
                if (split.length > 1) {
                    int selectPosition = stringToInt(split[1]);
                    if (selectPosition >= 0 && selectPosition < viewPagerExam.getAdapter().getCount()) {
                        viewPagerExam.setCurrentItem(selectPosition);
                    }
                }
            }
        } else if (!TextUtils.isEmpty(answer)) {
            showToast(answer);
        } else {
            showToast("答案生成失败");
        }

    }

    /**
     * 字符串转整型
     *
     * @param str 数据源
     * @return 如果异常返回-1；如果正常则返回正常值
     */
    private int stringToInt(String str) {
        int selectPosition;
        try {
            selectPosition = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            selectPosition = -1;
        }
        return selectPosition;
    }

    /**
     * 获取提交的答案
     *
     * @return 答案
     */
    private String getSubmitAnswer() {
        List<Page.Quesition.Answer> the_answer_list;
        String returnResult = "";
        if (questionList == null || questionList.isEmpty()) {
            return returnResult;
        }
        for (int i = 0; i < questionList.size(); i++) {
            the_answer_list = questionList.get(i).getChle();
            String result = "";
            String answers = "";
            // 判断是否有题没答完
            if (questionList.get(i).que_state == 0) {
                return "001+" + i;
            } else {
                result = paperId + "-" + questionList.get(i).getQues_Code()
                        + "-" + questionList.get(i).getQues_Id() + "-";
                for (int j = 0; j < the_answer_list.size(); j++) {
                    if (the_answer_list.get(j).ans_state == 1) {
                        try {
                            answers = answers + the_answer_list.get(j).getAnswer_Code() + ",";
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                result = result + answers + ";";
            }
            returnResult += result;
        }
        return returnResult;
    }

    /**
     * 检查是否全部答题
     */
    public void checkAnswerCompleted() {
        //如果循环结束后complete = true,说明全部答题
        boolean complete = true;
        if (questionList == null || questionList.isEmpty()) {
            tvSubmit.setTextColor(Color.parseColor("#999999"));
        }
        for (int i = 0; i < questionList.size(); i++) {
            // 判断是否有题没答完
            if (questionList.get(i).que_state == 0) {
                complete = false;
            }
        }
        //全部答题
        if (complete) {
            tvSubmit.setTextColor(Color.parseColor("#35CFAD"));
        } else {
            tvSubmit.setTextColor(Color.parseColor("#999999"));
        }
    }


    /**
     * 点击事件
     */
    public OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            switch (view.getId()) {
                case R.id.tvSubmit:
                    handleAnswerAndSubmit();
                    break;
                case R.id.llBack:
                    showToast("点击了返回按钮");
                    break;
                default:
                    break;
            }
        }
    };

    public static class MyHandler extends Handler {
        private WeakReference<ThirtyFourActivity> activity;

        public MyHandler(ThirtyFourActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ThirtyFourActivity ezActivity = activity.get();


        }
    }

    /**
     * Json转List集合,遇到解析不了的，就使用这个
     */
    public static <T> List<T> fromJsonList(String json, Class<T> cls) {
        List<T> mList = new ArrayList<T>();
        JsonArray array = new com.google.gson.JsonParser().parse(json).getAsJsonArray();
        Gson mGson = new Gson();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
