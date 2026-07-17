package model.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import model.dto.Department;
import model.exception.ServiceException;
import util.TestUtil;

/**
 * UC02【部門一覧表示】機能のテストクラス<br>
 *
 * @author Fullness, Inc.
 *
 */
@DisplayName("UC02【部門一覧表示】機能のテスト")
public class GetDepartmentListServiceTest {

    /**
     * テスト対象
     */
    GetDepartmentListService target;

    /**
     * 後処理
     * 
     * @throws Exception
     */
    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        TestUtil.initDB();
        TestUtil.setDS101ToDB();
        TestUtil.setDS001ToDB();
    }

    /**
     * 各テスト前に実行
     * 
     * @throws Exception
     */
    @BeforeEach
    public void setUp() throws Exception {
        TestUtil.initDB();
        target = new GetDepartmentListService();
    }

    @Test
    @DisplayName("リソースに登録されている全ての部門情報を複数取得:データあり")
    public void testGetDeptListWithDeptName01() throws Exception {
        TestUtil.setDS101ToDB();
        // TestUtil.setDS001ToDB();
        List<Department> expected = TestUtil.getDS101();
        List<Department> actual = target.readDepartmentAll();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("リソースに登録されている全ての部門情報を複数取得:データなし")
    public void testGetDeptListWithDeptName02() throws Exception {
        TestUtil.setDS101ToDB();
        // TestUtil.setDS002ToDB();
        List<Department> expected = TestUtil.getDS102();
        List<Department> actual = target.readDepartmentAll();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("リソースに登録されている全ての部門情報を複数取得:例外処理（取得失敗）")
    public void testGetDeptListWithDeptName03() throws Exception {
        TestUtil.clearDB();
        assertThrows(ServiceException.class, () -> target.readDepartmentAll());
    }

    @Test
    @DisplayName("リソースに登録されている全ての部門情報を複数取得:例外処理（DB処理エラー）")
    public void testGetDeptListWithDeptName04() throws Exception {
        TestUtil.changeDBSetting();
        try {
            assertThrows(ServiceException.class, () -> target.readDepartmentAll());
        } finally {
            TestUtil.resetDBSetting();
        }
    }
}
