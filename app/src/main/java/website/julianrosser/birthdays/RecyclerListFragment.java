package website.julianrosser.birthdays;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Main view. Fragment which holds the RecyclerView.
 */
public class RecyclerListFragment extends android.support.v4.app.Fragment {

    // String for easy recognition of class while logging
    String TAG = getClass().getSimpleName();

    // Reference to mAdapter
    public static RecyclerViewAdapter mAdapter;

    // Reference to recyclerView
    static RecyclerView recyclerView;

    // Reference to view which shows when list empty. todo - needed globally?
    static View emptyView;

    // Required empty constructor
    public RecyclerListFragment() {
    }


    /* Use newInstance in case in the future we want to add construction parameters or initialisation here */
    public static RecyclerListFragment newInstance() {
        Log.d("RecyclerListFragment", "newFragment");
        return new RecyclerListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate view
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Initialise important reference to the main view: RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);


        // Reference empty TextView
        emptyView = view.findViewById(R.id.empty_view);

        /* Detect whether the 'no birthdays found' message should be displayed instead of Rec.view.
         Using empty and recycler references. */
        showEmptyMessageIfRequired();

        // Set layout properties
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        // Can use this to optimize performance as RecyclerView will NOT change size.
        recyclerView.setHasFixedSize(true);

        mAdapter = new RecyclerViewAdapter(MainActivity.birthdaysList);

        recyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        emptyView = null;
        recyclerView = null;
    }

    // Show or hide the 'no birthdays found' message depending on size of birthday Array
    public static void showEmptyMessageIfRequired() { // todo - remove then replace to prevent redraw?

            if (MainActivity.birthdaysList.isEmpty()) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.INVISIBLE);
            }
    }
}