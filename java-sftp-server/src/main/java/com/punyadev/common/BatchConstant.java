package krungthai.coa.importbatch.common;

public class BatchConstant {

    private BatchConstant() {}

    public static final String APP_NAME = "COA Import Source Data";

    public static final String N = "N";

    public static final String EQTS = "EQTS";
    public static final String FITS = "FITS";
    public static final String REPO = "REPO";
    public static final String SUMMIT = "SUMMIT";

    public static final String PIPELINE_DELIMITER = "|";

    public static final String TEXT_EXTENSION = ".txt";

    public static final String IMPORT_SOURCE_DATA_SFTP = "IMPORT_SOURCE_DATA_SFTP";
    public static final String IP = "IP";
    public static final String PORT = "PORT";
    public static final String USER = "USER";
    public static final String PASSWORD = "PASSWORD";

    public static final String SYSTEM_BATCH = "system_batch";

    public static final String INSERT_VALUE = "values(?,?,?,?,?,?,?,?)";

    public static final String SQL_INSERT_REPO_MASTER = "INSERT INTO COA_source_repo_master (uuid,file_name_tran,file_name_ctrl,ctrl_date,ctrl_total_record,ctrl_sum_amount_dr,ctrl_sum_amount_cr,create_by)"
        + INSERT_VALUE;
    public static final String SQL_INSERT_REPO = "INSERT INTO COA_source_repo (id,uuid,asof_date,posting_date,value_date,ccy,trade_id,external_trade_id,account_id,external_id"
        + ",amount,product_lv1,product_lv2,product_lv3,product_code,port_desk,hedge_type,deal_type,dr_cr,cost_center"
        + ",channel_lv1,channel_lv2,channel_code,cif,event,sub_event,posting_type,posting_system,source,response_code"
        + ",swift_code,reserve_1,reserve_2,margin_amount,cost_center_sale,transaction_description,reference_number,ref1,ref2,ref3"
        + ",ref4,ref5,ref6,ref7,ref8,ref9,ref10,ref11,ref12,ref13"
        + ",ref14,ref15,ref16,ref17,ref18,ref19,ref20,create_by)"
        + " VALUES (?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?)";

    public static final String SQL_INSERT_SUMMIT_MASTER = "INSERT INTO COA_source_summit_master (uuid,file_name_tran,file_name_ctrl,ctrl_date,ctrl_total_record,ctrl_sum_amount_dr,ctrl_sum_amount_cr,create_by)"
        + INSERT_VALUE;
    public static final String SQL_INSERT_SUMMIT = "INSERT INTO COA_source_summit (id,uuid,asof_date,posting_date,value_date,ccy,trade_id,external_trade_id,account_id,external_id"
        + ",amount,product_lv1,product_lv2,product_lv3,product_code,port_desk,hedge_type,deal_type,dr_cr,cost_center"
        + ",channel_lv1,channel_lv2,channel_code,cif,event,sub_event,posting_type,posting_system,source,response_code"
        + ",swift_code,reserve_1,reserve_2,margin_amount,cost_center_sale,transaction_description,reference_number,ref1,ref2,ref3"
        + ",ref4,ref5,ref6,ref7,ref8,ref9,ref10,ref11,ref12,ref13"
        + ",ref14,ref15,ref16,ref17,ref18,ref19,ref20,create_by)"
        + " VALUES (?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?)";

    public static final String SQL_INSERT_FITS_MASTER = "INSERT INTO COA_source_fits_master (uuid,file_name_tran,file_name_ctrl,ctrl_date,ctrl_total_record,ctrl_sum_amount_dr,ctrl_sum_amount_cr,create_by)"
        + INSERT_VALUE;
    public static final String SQL_INSERT_FITS = "INSERT INTO COA_source_fits (id,uuid,asof_date,posting_date,value_date,ccy,trade_id,external_trade_id,account_id,external_id"
        + ",amount,product_lv1,product_lv2,product_lv3,product_code,port_desk,hedge_type,deal_type,dr_cr,cost_center"
        + ",channel_lv1,channel_lv2,channel_code,cif,event,sub_event,posting_type,posting_system,source,response_code"
        + ",swift_code,reserve_1,reserve_2,margin_amount,cost_center_sale,transaction_description,reference_number,ref1,ref2,ref3"
        + ",ref4,ref5,ref6,ref7,ref8,ref9,ref10,ref11,ref12,ref13"
        + ",ref14,ref15,ref16,ref17,ref18,ref19,ref20,create_by)"
        + " VALUES (?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?)";

    public static final String SQL_INSERT_EQTS_MASTER = "INSERT INTO COA_source_eqts_master (uuid,file_name_tran,file_name_ctrl,ctrl_date,ctrl_total_record,ctrl_sum_amount_dr,ctrl_sum_amount_cr,create_by)"
        + INSERT_VALUE;
    public static final String SQL_INSERT_EQTS = "INSERT INTO COA_source_eqts (id,uuid,asof_date,posting_date,value_date,ccy,trade_id,external_trade_id,account_id,external_id"
        + ",amount,product_lv1,product_lv2,product_lv3,product_code,port_desk,hedge_type,deal_type,dr_cr,cost_center"
        + ",channel_lv1,channel_lv2,channel_code,cif,event,sub_event,posting_type,posting_system,source,response_code"
        + ",swift_code,reserve_1,reserve_2,margin_amount,cost_center_sale,transaction_description,reference_number,ref1,ref2,ref3"
        + ",ref4,ref5,ref6,ref7,ref8,ref9,ref10,ref11,ref12,ref13"
        + ",ref14,ref15,ref16,ref17,ref18,ref19,ref20,create_by)"
        + " VALUES (?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?,?,?"
        + ",?,?,?,?,?,?,?,?)";

    public static final String AS_OF_DATE = "asOfDate";
    public static final String SOURCE = "source";
    public static final String START_AT = "startAt";

    public static final String FILE_NAME_REPO = "FILE_NAME_REPO";
    public static final String FILE_NAME_REPO_CTRL = "FILE_NAME_REPO_CTRL";
    public static final String PATH_PVC_REPO = "PATH_PVC_REPO";
    public static final String PATH_SFTP_REPO = "PATH_SFTP_REPO";

    public static final String FILE_NAME_SUMMIT = "FILE_NAME_SUMMIT";
    public static final String FILE_NAME_SUMMIT_CTRL = "FILE_NAME_SUMMIT_CTRL";
    public static final String PATH_PVC_SUMMIT = "PATH_PVC_SUMMIT";
    public static final String PATH_SFTP_SUMMIT = "PATH_SFTP_SUMMIT";

    public static final String FILE_NAME_FITS = "FILE_NAME_FITS";
    public static final String FILE_NAME_FITS_CTRL = "FILE_NAME_FITS_CTRL";
    public static final String PATH_PVC_FITS = "PATH_PVC_FITS";
    public static final String PATH_SFTP_FITS = "PATH_SFTP_FITS";

    public static final String FILE_NAME_EQTS = "FILE_NAME_EQTS";
    public static final String FILE_NAME_EQTS_CTRL = "FILE_NAME_EQTS_CTRL";
    public static final String PATH_PVC_EQTS = "PATH_PVC_EQTS";
    public static final String PATH_SFTP_EQTS = "PATH_SFTP_EQTS";

    public static final String PATH_ARCHIVE_SFTP = "PATH_ARCHIVE_SFTP";

}
