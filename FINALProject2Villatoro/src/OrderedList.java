import java.util.List;

public class OrderedList {
    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list){
        boolean isSorted = true;
        for(int i = list.size()-1; i > 0 ; i--){
            T current = list.get(i);
            if(!checkSorted(list, current)){
                isSorted = false;
            }
        }
        return isSorted;
    }

    private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T current) {
//maybe there is a more elegant way to do this
        T currentValue = list.get(list.indexOf(current));
        T nextValue = list.get(list.indexOf(current) - 1);

        if (nextValue != null) {
//make sure to check for null values when comparing
            return currentValue.compareTo(nextValue) >= 0; //if next index is larger return false
        }
        return true;
    }
}



