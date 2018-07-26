package com.example.zhangtongxin0323.myapplication222.fg;


import android.Manifest;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.zhangtongxin0323.myapplication222.R;
import com.google.gson.Gson;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhangtongxin0323 on 2018/5/27.
 */

public class TestFragment extends Fragment {
    private  Activity mAct;

    private ArrayList arr = new ArrayList();

    private ListView mListView;
    private MyBaseAdapter myBaseAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
//
//        arr.add("hello word!!!!!!!!!!!!!!!!!!!!!!!!");
        mAct=getActivity();
        myBaseAdapter = new MyBaseAdapter(mAct, arr);
        View view = inflater.inflate(R.layout.main, container, false);
        mListView = view.findViewById(R.id.listview1);
        mListView.setAdapter(myBaseAdapter);
            Thread thread=new Thread(new Runnable() {

                @Override
                public void run() {
                    String str_json;
                    str_json=get();
                    Gson gson = new Gson();


                    //          Test test = gson.fromJson(str_json, Test.class);
                   final DataBean t =gson.fromJson(str_json,DataBean.class);
                    mAct.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myBaseAdapter.setmList(t.getList());
                            myBaseAdapter.notifyDataSetChanged();

                        }
                    });
                    //           arr =t.getList();
                    //           Log.i("aaa"+t,t.getList().get(0).getNickname());




                }
            });
            thread.start();


//        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STRONGE);
//        }//REQUEST_EXTERNAL_STRONGE是自定义个的一个对应码，用来验证请求是否通过
//        else {
//
//            Thread t1 = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    final Bitmap bitmap = getImageByDownload("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527393974287&di=aa190c0c05bc07461f78aa154a1c2038&imgtype=0&src=http%3A%2F%2Fimg.taopic.com%2Fuploads%2Fallimg%2F120418%2F94799-12041Q3002761.jpg"
//                    );
//                    mAct.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ivTestUsed.setImageBitmap(bitmap);
//
//
//                        }
//                    });
//                }
//            });
//            t1.start();
//
//        }
        return view;
    }






    private String get(){
        String path = "http://45.76.199.143/static/chatlist.json";
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            //获得结果码
            int responseCode = connection.getResponseCode();
            if(responseCode ==200){
                //请求成功 获得返回的流
                InputStream is = connection.getInputStream();
                String a=getTextFromStream(is);
                Log.i("xxx",a);
                return a;
               // return getTextFromStream(is);
            }else {
                //请求失败
                return null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }


    //字节流转成字符流
    public static String getTextFromStream(InputStream inputStream) {

        byte[] b = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len =  inputStream.read(b))!= -1){
                bos.write(b,0,len);
            }
            //把流中的数据转成字节数组的形式,然后用字节数组构造一个字符串
            byte[] bytes = bos.toByteArray();
            bos.close();
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



//    //方法：发送网络请求，获取百度首页的数据。在里面开启线程
//    private void sendRequestWithHttpClient() {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                //用HttpClient发送请求，分为五步
//                //第一步：创建HttpClient对象
//                HttpClient httpCient = new DefaultHttpClient();
//                //第二步：创建代表请求的对象,参数是访问的服务器地址
//                HttpGet httpGet = new HttpGet("http://www.baidu.com");
//
//                try {
//                    //第三步：执行请求，获取服务器发还的相应对象
//                    HttpResponse httpResponse = httpCient.execute(httpGet);
//                    //第四步：检查相应的状态是否正常：检查状态码的值是200表示正常
//                    if (httpResponse.getStatusLine().getStatusCode() == 200) {
//                        //第五步：从相应对象当中取出数据，放到entity当中
//                        HttpEntity entity = httpResponse.getEntity();
//                        String response = EntityUtils.toString(entity,"utf-8");//将entity当中的数据转换为字符串
//                        //在子线程中将Message对象发出去
//                        Message message = new Message();
//                        message.what = SHOW_RESPONSE;
//                        message.obj = response.toString();
//                        handler.sendMessage(message);
//                    }
//
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//            }
//        }).start();//这个start()方法不要忘记了





//    private Bitmap getImageByDownload(String strurl) {
//        URL url = null;
//
//
//        HttpURLConnection con = null;
//        try {
//            url = new URL(strurl);
//            con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.setReadTimeout(5000);
//            con.setDoInput(true);
            //InputStream in = con.getInputStream();
//            File parent = Environment.getExternalStorageDirectory();
//            File file = new File(parent, String.valueOf(System..e(TAG, "run: path---" + file.getAbsolutePath());
//
//            byte ch[] = new byte[2 * 1024];
//            int len;
//            if (fos != null) {
//                while ((len = in.read(ch)) != -1) {
//                    fos.write(ch, 0, len);
//                }
//                in.close();
//                fos.close();
//            }
            /*BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            BufferedWriter bw = null;
            String s;
            while ((s = br.readLine()) != null){
                bw = new BufferedWriter(new OutputStreamWriter(fos));
                //以字符串的形式写入数据，无法读取图片
                bw.write(s);
            }
            br.close();
            bw.close();*/
//            final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                if (bitmap != null){
//                    iv.setImageBitmap(bitmap);
//                }
//            }
//        });
//            return bitmap;
//
//catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
}
