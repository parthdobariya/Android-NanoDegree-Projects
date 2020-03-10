package app.parth.in.capstoneprojectstage_2.widgets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;
import java.util.List;

import androidx.room.Room;
import app.parth.in.capstoneprojectstage_2.R;
import app.parth.in.capstoneprojectstage_2.database.Quote;
import app.parth.in.capstoneprojectstage_2.database.QuoteDatabase;
import app.parth.in.capstoneprojectstage_2.model.Quotes;

public class StackWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<Quotes> widgetItems = new ArrayList<>();
    private Context context;

    public StackRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
    }

    // Initialize the data set.
    public void onCreate() {
        // In onCreate() you set up any connections / cursors to your data source. Heavy lifting,
        // for example downloading or creating content etc, should be deferred to onDataSetChanged()
        // or getViewAt(). Taking more than 20 seconds in this call will result in an ANR.

        QuoteDatabase quoteDatabase = Room.databaseBuilder(context, QuoteDatabase.class, "quote_db")
                .allowMainThreadQueries().build();

        List<Quote> quoteDaoList = quoteDatabase.quoteDao().getAll();

        for (Quote quoteDao : quoteDaoList) {
            Quotes quotes = new Quotes(quoteDao.getAuthor(), quoteDao.getCategory(), quoteDao.getTitle());
            widgetItems.add(quotes);
        }
    }

    @Override
    public void onDestroy() {
        widgetItems.clear();
    }

    @Override
    public int getCount() {
        return widgetItems.size();
    }

    // Given the position (index) of a WidgetItem in the array, use the item's text value in
    // combination with the app widget item XML file to construct a RemoteViews object.
    public RemoteViews getViewAt(int position) {
        // position will always range from 0 to getCount() - 1.

        // Construct a RemoteViews item based on the app widget item XML file, and set the
        // text based on the position.
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_stack_item);
        rv.setTextViewText(R.id.widget_text, widgetItems.get(position).getTitle());

        // Next, set a fill-intent, which will be used to fill in the pending intent template
        // that is set on the collection view in StackWidgetProvider.
        Bundle extras = new Bundle();
        extras.putInt(StackWidgetProvider.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        // Make it possible to distinguish the individual on-click
        // action of a given item
        rv.setOnClickFillInIntent(R.id.widget_text, fillInIntent);

        // Return the RemoteViews object.
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onDataSetChanged() {

    }
}
