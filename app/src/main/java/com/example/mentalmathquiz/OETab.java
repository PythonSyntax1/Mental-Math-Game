package com.example.mentalmathquiz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.time.LocalTime;
import java.util.ArrayList;

import static com.example.mentalmathquiz.MainMenu.user;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OETab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OETab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OETab extends Fragment {

    private ListView listView;

    private OnFragmentInteractionListener mListener;

    public OETab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OETab.
     */
    // TODO: Rename and change types and number of parameters
    public static OETab newInstance(String param1, String param2) {
        OETab fragment = new OETab();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_oetab, container, false);
        ResultsActivity resultsActivity = (ResultsActivity) getActivity();
        String length = resultsActivity.getQuizLength();
        Integer quizlength = Integer.parseInt(length);


        listView = view.findViewById(R.id.oeListView);
        if (user.oeContains(Integer.parseInt(length))) {

            ArrayList<LocalTime> currentList = user.getOEList(quizlength);
            ArrayList<String> convertedList = new ArrayList<>();
            for (LocalTime lt: currentList) {
                convertedList.add(lt.toString());
            }

            ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, convertedList);
            listView.setAdapter(adapter);
        }
        return view;
    }

/*    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
