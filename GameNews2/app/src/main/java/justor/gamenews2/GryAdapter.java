package justor.gamenews2;

        import android.support.v7.widget.RecyclerView;
        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import org.w3c.dom.Document;
        import org.w3c.dom.Element;

public class GryAdapter extends RecyclerView.Adapter<GryAdapter.GryViewHolder> implements GryTask.DocumentConsumer {


    public interface URLLoader {
        void load(String title, String url);
    }

    private final URLLoader _urlLoader;

    private Document _document=null;


    public GryAdapter(URLLoader urlloader) {
        _urlLoader=urlloader;
    }

    @Override
    public int getItemCount() {
        if(_document!=null)
        {return _document.getElementsByTagName("item").getLength();}
        else
        {return 0;}
    }


    @Override
    public GryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new GryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GryViewHolder holder, int position) {
        Element item = (Element) _document.getElementsByTagName("item").item(position);
        holder.setElement(item);
    }

    @Override
    public void setXMLDocument(Document document)
    {
        _document=document;
        notifyDataSetChanged();
    }


    public class GryViewHolder extends RecyclerView.ViewHolder {

        private final TextView _title;
        public String url;
        public String send_title;
        private Element _element;

        public GryViewHolder(final View itemView) {
            super(itemView);

            _title = itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // i could have bind variables with data here (but I did it in setElement method)
                    final Context context=itemView.getContext();
                    Intent myIntent = new Intent(context, Article.class);
                    myIntent.putExtra("url", url);
                    myIntent.putExtra("title", send_title);
                    _urlLoader.load(send_title,url);
                  //  context.startActivity(myIntent);
                }
            });
        }

        public void setElement(Element element)
        {_element=element;
            _title.setText(element.getElementsByTagName("title").item(0).getTextContent());
            url = element.getElementsByTagName("link").item(0).getTextContent();
            send_title = element.getElementsByTagName("title").item(0).getTextContent();
        }
    }
}