package c.example.xmlparsingsax;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends Activity {
    String url = "http://feeds.feedburner.com/ndtvnews-top-stories";
    RecyclerView recyclerView;
    Context context;
    News news;
    ArrayList<News> newsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        newsArrayList=new ArrayList<>();
        new MyAsyncTask().execute(url);

    }

    class MyAsyncTask extends AsyncTask<String, Void, String> {

        ProgressDialog pd;

        @Override
        protected String doInBackground(String... strings) {
            doXmlParsing();
            return null;
        }

        private void doXmlParsing() {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            try {
                SAXParser parser = saxParserFactory.newSAXParser();
                DefaultHandler defaultHandler = new DefaultHandler() {
                    boolean title, link, pubDate, description, fullImage;

                    @Override
                    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                        super.startElement(uri, localName, qName, attributes);

                        if (localName.equals("title")) {
                            news = new News();
                            title = true;
                        } else if (localName.equals("link")) {
                           // news = new News();
                            link = true;
                        } else if (localName.equals("pubDate")) {
                           // news = new News();
                            pubDate = true;
                        } else if (localName.equals("description")) {
                           // news = new News();
                            description = true;
                        } else if (localName.equals("fullimage")) {
                           // news = new News();
                            fullImage = true;

                        }
                    }

                    @Override
                    public void endElement(String uri, String localName, String qName) throws SAXException {
                            super.endElement(uri, localName, qName);

                        if (localName.equals("title")) {
                            title = false;
                        } else if (localName.equals("link")) {
                            link = false;
                        } else if (localName.equals("pubDate")) {
                            pubDate = false;
                        } else if (localName.equals("description")) {
                            description = false;
                        } else if (localName.equals("fullimage")) {
                            fullImage = false;
                            newsArrayList.add(news);
                        }
                    }

                    @Override
                    public void characters(char[] ch, int start, int length) throws SAXException {
                        super.characters(ch, start, length);
                        if (title) {
                           news.setTitle(new String(ch,start,length));
                        } else if (link) {
                            news.setLink(new String(ch,start,length));
                        } else if (pubDate) {
                            news.setPubDate(new String(ch,start,length));
                        } else if (description) {
                            news.setDescription(new String(ch,start,length));
                        } else if (fullImage){
                            news.setFullImage(new String(ch,start,length));
                        }
                    }
                };
parser.parse(url,defaultHandler);

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = ProgressDialog.show(context, "wait", "Loading...");

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            NewsAdapter adapter=new NewsAdapter(newsArrayList,context);
            recyclerView.setAdapter(adapter);

        }

    }

}
