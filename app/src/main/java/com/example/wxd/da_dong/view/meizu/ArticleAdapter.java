package com.example.wxd.da_dong.view.meizu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.wxd.da_dong.R;
import com.recyclerview.GroupRecyclerAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 适配器
 * Created by huanghaibin on 2017/12/4.
 */

public class ArticleAdapter extends GroupRecyclerAdapter<String, Article> {


    private RequestManager mLoader;

    public ArticleAdapter(Context context) {
        super(context);
        mLoader = Glide.with(context.getApplicationContext());
        LinkedHashMap<String, List<Article>> map = new LinkedHashMap<>();
        List<String> titles = new ArrayList<>();
        map.put("今日快讯", create(0));
        map.put("精选故事", create(1));
        map.put("宝贝计划", create(2));
        titles.add("今日快讯");
        titles.add("精选故事");
        titles.add("宝贝计划");
        resetGroups(map,titles);
    }


    @Override
    protected RecyclerView.ViewHolder onCreateDefaultViewHolder(ViewGroup parent, int type) {
        return new ArticleViewHolder(mInflater.inflate(R.layout.item_list_article, parent, false));
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, Article item, int position) {
        ArticleViewHolder h = (ArticleViewHolder) holder;
        h.mTextTitle.setText(item.getTitle());
        h.mTextContent.setText(item.getContent());
        mLoader.load(item.getImgUrl())
                .asBitmap()
                .centerCrop()
                .into(h.mImageView);

    }

    private static class ArticleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle,
                mTextContent;
        private ImageView mImageView;

        private ArticleViewHolder(View itemView) {
            super(itemView);
            mTextTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTextContent = (TextView) itemView.findViewById(R.id.tv_content);
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }


    private static Article create(String title, String content, String imgUrl) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setImgUrl(imgUrl);
        return article;
    }

    private static List<Article> create(int p) {
        List<Article> list = new ArrayList<>();
        if (p == 0) {
            list.add(create("习近平主席将同莫迪总理举行非正式会晤",
                    " 4月27日至28日，习近平主席同莫迪总理在湖北省武汉市举行非正式会晤。",
                    "http://p3.pstatp.com/list/640x360/7b990018aa2e082e6d3b"));
            list.add(create("朝韩首脑会晤！金正恩步行踏上韩国 首次检阅韩国三军仪仗队",
                    "海外网4月27日电 据韩联社报道，北京时间27日上午8点30分，朝鲜最高领导人金正恩南下板门店，步行跨越军事分界线（又称三八线）后进入韩方一侧，韩国总统文在寅在军事分界线处等候。据了解，这是朝鲜战争后朝鲜最高领导人首次踏上韩国土地",
                    "https://p3.pstatp.com/list/190x124/7e3e0002596d3e7827e2"));
            list.add(create("美国“封杀”中兴谁遭殃？多家美企已“躺枪”",
                    " 外媒称，美国政府宣布禁止美国厂商向中国电信大厂中兴通讯供应芯片、天线等零组件7年。这项禁令使数家美国供货商也恐流失大笔营收，近期股价一蹶不振。中兴通讯25日晚发布公告称，公司管理层已决定采取相关美国法律下可采取的与美国政府命令相关的某些行动，股票继续停牌。",
                    "https://images.shobserver.com/news/440_250/2018/4/27/5fdfb533-dc95-4b85-a5f2-3fd87ddc7eb7.jpg"));
            list.add(create("今年养老金调整何时发放到位？人社部这么回应",
                    "【中新网客户端北京4月27日电(记者 李金磊)今年养老金调整何时发放到位，是退休人员非常关心的问题。人社部新闻发言人卢爱红27日表示，各地要在5月31日前制定具体实施方案，报送人社部、财政部审批后实施，尽快将增加的养老金发放到退休人员手里",
                    "http://p3.pstatp.com/large/7e3e0002bf192a7d7ee4"));
            list.add(create("北京车展：国产轿车对比 红旗H5 VS 吉利博瑞，到底谁好",
                    "导语: 在本届北京车展上，红旗公布了旗下新车型H5、以及它14.98-19.58万元的售价。虽说新车拥有完善的驾驶辅助系统、莲花团队调教的底盘和全LED头灯，当在国产自主品牌的B级车市场中，这样的价格还是有些令人惊讶。",
                    "http://p3.pstatp.com/list/640x360/7e52000cb60f0b4c320c"));
        } else if (p == 1) {
            list.add(create(
                    "小鸡的脸红了",
                    "一个阳光明媚的早晨，小鸡看到头顶上金黄的太阳，懒懒地说：“今天的阳光真好，我要好好地享受享受阳光浴。” 　　小鸡躺在草地上，正享受着暖暖的阳光。突然，听到小猪的声音：“小鸡，别晒太阳了，快来帮我盖房子吧。”小鸡说：“不干不干，盖房子又脏又费力气，我要在这儿晒太阳呢。”小猪的话音刚落，小鸡又听到小鸭的声音：“小鸡，快来帮我磨面吧。”小鸡又说：“不干不干，磨面太累了，我正在享受阳光浴呢。” 　　晚上，忽然下起了大雨，小鸡的房子漏雨了，小鸡拿来水桶、脸盆接雨，小猪和小鸭看见了，赶紧把小鸡的房子修好了。 　　小鸡看到修好的房子，再看看浑身湿透了的小猪和小鸭，不好意思地低下了头。小猪说：“大家相互帮助是应该的。”这时，小鸡的脸红红的，像一个红红的大苹果。",
                    "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1280065307,4218687617&fm=27&gp=0.jpg"));
            list.add(create(
                    "土狼和小羊",
                    "土狼抓住一只小羊，喜滋滋的说：“哈哈！今晚我们狼弟兄几个可以美餐一顿啦！” 　　说着，就扛起小羊，往狼洞走去。 　　来到狼洞，土狼见狼弟兄们都在，刚要开口说话，岂料小羊大声说：“土狼先生，没想到你这么自私，不顾你的狼兄狼弟，想一人独吞我！” 　　狼弟兄们听了小羊这话，顿生怒火，纷纷和土狼争吵起来，小羊呢？当然是趁机逃走了。",
                    "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3477617846,3248676384&fm=27&gp=0.jpg"));
            list.add(create(
                    "刺猬凳子",
                    "猴子是动物群中的小头目，因此非常的任性放肆，一直让大家很受不了。有一天，猴子 对兔子说： “今天天气真好，我们去尖山玩好吗？” 兔子摇着长耳朵拒绝了。 猴子觉得很不舒服，又约狸一起去。可是狸也拒绝了。 猴子更加不高兴，又邀请了狐狸。狐狸也不喜欢任性的猴子，又拒绝了它。 猴子被拒绝之后，不知道该做什么好，所以心里虽然不高兴，但仍然去了尖山。 猴子爬上尖山，看到有只刺猬缩成球状在睡午觉。 “唷！喂！起来！小头目来罗！” “吵死了！不要打扰我睡午觉！” “唉呀！这么小竟然这么狂妄自大，看我不拿你当作我的凳子才怪！”猴子看不起刺 猬，就坐了下来。 刺猬一怒，就把背上的刺全都竖了起来。 “啊！好痛！呀！”于是，猴子抱着屁股跳了起来。",
                    "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2878395851,2975646331&fm=11&gp=0.jpg"));
            list.add(create(
                    "小晶晶的故事",
                    "夏天的晚上，青蛙在池塘边开纳凉晚会，邀请朋友们参加，蜻蜓、蝴蝶、螳螂、蚱蜢都来了。天上的星星小晶晶对月亮妈妈说：“我也想去参加纳凉晚会!”“好吧，你一定要在天亮之前回来，不然的话，你就会变成萤火虫，再也不能回来了。”小晶晶点点头，飞到了池塘边。 　　晚会开始，蝴蝶姑娘跳了一个《蝶恋花》，螳螂当了杂技演员，蜻蜓的《飞行健美操》也很精彩。青蛙向大家报幕：“现在，请小晶晶表演。”小晶晶大方地走上舞台，唱了一首《小星星眨眼睛》，将晚会推向了高潮。 　　节目一个接一个，小晶晶玩得真开心，把妈妈的话全忘了。天亮以后，小晶晶变成一只萤火虫，再也回不去了。它在花草间不停地飞呀飞，仍然很快活!",
                    "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2738800367,1536850541&fm=27&gp=0.jpg"));
        } else if (p == 2) {
            list.add(create(
                    "一屋不扫，何以扫天下",
                    "东汉时期有一个人叫陈蕃，他学识渊博，胸怀大志，少年时代发奋读书，以天下为己任。一天，他父亲的一位老朋友薛勤来看他，见他独居的院内杂草丛生、秽物满地，就对他说：“你怎么不打扫一下屋子，以招待宾客呢？”\n" +
                            "\n" +
                            "陈蕃回答：“大丈夫处世，当扫天下，安事一屋乎！”薛勤当即反问道：“一屋不扫，何以扫天下？”陈蕃听了无言以对，觉得很有道理。从此，他开始注意从身边小事做起，最终成为一代名臣。",
                    "http://img.mp.itc.cn/upload/20170221/0ef4732f9ee540279ce8c1a03a396fab_th.jpg"));
            list.add(create(
                    "书读百遍，其意自见",
                    "三国时期，魏国有一个人叫董遇，自幼生活贫苦，整天为了生活而奔波。但是，他只要一有空闲时间，就坐下来读书学习，所以知识很渊博。他的哥哥讥笑他，他却不在乎。\n" +
                            "\n" +
                            "天长地久，他写出了两本书，引起了轰动，别人问他读书有什么窍门。他说：“书读百遍，其义自见（xiàn）。”人们很佩服他，他的名声也越来越大。附近的人纷纷前来求教，并问他是如何学习的。\n" +
                            "\n" +
                            "董遇告诉他们说：“冬者，岁之余；夜者，日之余；阴雨者，时之余。学习要利用三余，也就是三种的空余时间：冬天是一年之余，晚上是一天之余，雨天是平日之余。”\n" +
                            "\n" +
                            "人们听了，恍然大悟。原来就是要通过一切可以利用的时间来读书学习，以提高自己的水平。",
                    "http://img.mp.itc.cn/upload/20170221/4d877e525002429fa446e4d08ab71c06_th.jpg"));
            list.add(create("孟母断机",
                    "孟子小时候厌倦学习，有一天不愿读书，就逃回了家。孟母正好在织布，见他逃学回来，一句话没讲，就把织布的梭子给弄断了，这意味着马上将要织成的一匹布全毁了。孟子非常孝顺，忙跪下来问：“您为什么要这样？”\n" +
                            "\n" +
                            "孟母告诉他：“读书求学不是一两天的事，就像我织布，必须从一根根线开始，然后一寸一寸地才能织成一匹布，而布只有织成一匹了，才有用，才可以做衣服。读书也是这个道理，如果不能持之以恒，像你这样半途而废、浅尝辄止，以后怎能成才呢？”若是现在的孩子，可能不懂反省自己，一句话就扔给母亲：“这是你自己的事。”但孟子的根基毕竟不同一般，他如梦初醒、恍然大悟，从此一心向学，再也不随便旷课，后来继孔子而成为“亚圣”。\n" +
                            "\n",
                    "http://img.mp.itc.cn/upload/20170221/ea14aed6d3784317b4f5c4e87cf64ead_th.jpg"));
            list.add(create("黄香温席",
                    "古代有个叫黄香的人，以孝出名。他9岁时母亲去世，从此他更细心地照顾父亲，一人包揽了所有的家务事。到了冬天，他害怕父亲着凉，就先钻到冰冷的被窝里，用身体温热被子后，再扶父亲上床睡下。\n" +
                            "\n" +
                            "到了夏天，为了使父亲晚上能很快入睡，他每晚都先把凉席扇凉，再请父亲去睡。黄香小小年纪，就有这样的孝心，也使他做人、求学上有所成就，后来他当上了以孝闻名的好官，人称“天下无双，江夏黄香”，被列为“二十四孝”之一。\n" +
                            "\n",
                    "http://img.mp.itc.cn/upload/20170221/65d8b85842f54211bf20ea11877df44f_th.jpg"));
            list.add(create(
                    "相煎何急",
                    "以前在三国时期，曹丕得了王位后，因妒忌弟弟曹植的才华，就故意刁难他，让他七步成诗，题目是“兄弟”，但不能出现“兄弟”二字，作不出来就要砍头。\n" +
                            "\n" +
                            "所幸的是，曹植才华横溢、出口成章，很快就作了一首诗：“煮豆燃豆萁，豆在釜中泣，本是同根生，相煎何太急？”诗中用了一个比喻：豆子被放在锅里煮，而用来煮豆的是豆秆，所以豆子在锅里哭泣：“我们本为同根所生，何必要急于相残？”以此来影射曹丕的狠心。曹丕听后，心感惭愧，就不杀曹植了。",
                    "http://img.mp.itc.cn/upload/20170221/6e13205ee0894fea8b4d5e6f148a70f5.jpg"));
        }
        return list;
    }
}
