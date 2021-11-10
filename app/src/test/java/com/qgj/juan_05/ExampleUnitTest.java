package com.qgj.juan_05;

import androidx.lifecycle.Observer;

import com.qgj.juan_05.adpater.ServicesBusLineAdapter;
import com.qgj.juan_05.netwok.model.BusLienModel;
import com.qgj.juan_05.netwok.model.BusLineInfoModel;
import com.qgj.juan_05.netwok.service.ServiceDaoImpl;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        assertEquals(4, 2 + 2);
    }

    @Test
    public void test1() throws IOException {
        BusLienModel busLienAll = ServiceDaoImpl.getBusLienAll();
        for (BusLienModel.RowsDTO row : busLienAll.getRows()) {
            BusLineInfoModel busLienInfoById = ServiceDaoImpl.getBusLienInfoById(row.getId());
//            System.out.println(row.getId()+busLienInfoById.toString());

            List<BusLineInfoModel.RowsDTO> rows = busLienInfoById.getRows();
            row.mInfoModel=rows;
        }
        System.out.println(busLienAll.getRows().get(0).mInfoModel);
//        BusLineInfoModel busLienInfoById = ServiceDaoImpl.getBusLienInfoById(1);
    }
}