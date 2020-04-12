package com.nhl.dflib.docs;

import com.nhl.dflib.DataFrame;
import com.nhl.dflib.JoinType;
import com.nhl.dflib.Series;
import com.nhl.dflib.concat.SeriesConcat;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static java.util.Arrays.asList;

public class ConcatExample extends BaseExample {

    @Test
    public void concatSeries() {

// tag::concatSeries[]
        Series<String> s1 = Series.forData("x", "y", "z");
        Series<String> s2 = Series.forData("a");
        Series<String> s3 = Series.forData("m", "n");

        Series<String> sConcat = s1.concat(s2, s3);
// end::concatSeries[]

        print("concatSeries", sConcat);
    }

    @Test
    public void concatSeries_Self() {

// tag::concatSeries_Self[]
        Series<String> s = Series.forData("x", "y");
        Series<String> sConcat = s.concat(s);
// end::concatSeries_Self[]

        print("concatSeries_Self", sConcat);
    }

    @Test
    public void concatSeries_Static() {

// tag::concatSeries_Static[]
        Collection<Series<String>> ss = asList(
                Series.forData("x", "y", "z"),
                Series.forData("a"),
                Series.forData("m", "n"));

        Series<String> sConcat = SeriesConcat.concat(ss);
// end::concatSeries_Static[]

        print("concatSeries_Static", sConcat);
    }

    @Test
    public void vConcat() {

// tag::vConcat[]
        DataFrame df1 = DataFrame.newFrame("a", "b").foldByRow(
                1, 2,
                3, 4);

        DataFrame df2 = DataFrame.newFrame("a", "b").foldByRow(
                5, 6,
                7, 8);

        DataFrame dfv = df1.vConcat(df2); // <1>
// end::vConcat[]

        print("vConcat", dfv);
    }

    @Test
    public void hConcat() {

        DataFrame df1 = DataFrame.newFrame("a", "b").foldByRow(
                1, 2,
                3, 4);

        DataFrame df2 = DataFrame.newFrame("a", "b").foldByRow(
                5, 6,
                7, 8);

// tag::hConcat[]
        DataFrame dfh = df1.hConcat(df2); // <1>
// end::hConcat[]

        print("hConcat", dfh);
    }

    @Test
    public void vConcat_InnerMismatch() {

// tag::vConcat_InnerMismatch[]
        DataFrame df1 = DataFrame.newFrame("b", "a").foldByRow(
                1, 2,
                3, 4);

        DataFrame df2 = DataFrame.newFrame("a", "c").foldByRow( // <1>
                5, 6,
                7, 8);

        DataFrame dfv = df1.vConcat(JoinType.inner, df2); // <2>
// end::vConcat_InnerMismatch[]

        print("vConcat_InnerMismatch", dfv);
    }

    @Test
    public void vConcat_LeftMismatch() {

        DataFrame df1 = DataFrame.newFrame("b", "a").foldByRow(
                1, 2,
                3, 4);

        DataFrame df2 = DataFrame.newFrame("a", "c").foldByRow(
                5, 6,
                7, 8);

// tag::vConcat_LeftMismatch[]
        DataFrame dfv = df1.vConcat(JoinType.left, df2);
// end::vConcat_LeftMismatch[]

        print("vConcat_LeftMismatch", dfv);
    }

    @Test
    public void hConcat_LeftMismatch() {

// tag::hConcat_LeftMismatch[]
        DataFrame df1 = DataFrame.newFrame("a", "b").foldByRow(
                1, 2,
                3, 4,
                5, 6);

        DataFrame df2 = DataFrame.newFrame("c", "d").foldByRow(
                7, 8,
                9, 10);

        DataFrame dfv = df1.hConcat(JoinType.left, df2);
// end::hConcat_LeftMismatch[]

        print("hConcat_LeftMismatch", dfv);
    }
}
