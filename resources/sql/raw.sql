-- :name query-stations :? :*
-- :doc retrieves all station data
SELECT *
FROM PVIEW.VW_IOP_BTZS_CELL_EXP_D

-- :name query-activity-users :? :*
-- :doc retrieves a activity users with id
SELECT msisdn as msisdn, city_id as city_id, ext01 as ext01, ext02 as ext02
FROM  IOPDATA.IOP_MARKET_CUST_VIEW
WHERE svc_type = :code and channel_type = :channel_type
AND END_DATE > CURDATE()

-- :name query-marketing-users :? :*
-- :doc retrieves a activity users with id
SELECT msisdn as msisdn, city_id as city_id, channel_id as channel_id, ext02 as ext02
FROM  IOPDATA.IOP_MARKET_CUST_VIEW
WHERE svc_type = :code

-- :name query-marketing-users-for-outcall :? :*
-- :doc retrieves a activity users with id
SELECT msisdn as msisdn, city_id as city_id, channel_id as channel_id
				, EXT02 as ext02
FROM  IOPDATA.IOP_MARKET_CUST_VIEW
WHERE svc_type = :code and channel_type = :channel_type

-- :name query-activity-data :? :1
-- :doc retrieves a activity data with id
SELECT  B.MPSID  AS activity_id
	           ,COALESCE(C.MPSUBNAME,'-') AS activity_name
	           ,B.SVC_TYPE AS svc_type
						 ,B.CHANNELDESC AS svc_desc
	           ,A.EFCT_DATE AS start_date
	           ,A.END_DATE  AS end_date
						 ,D.USER_ID AS create_id
	           ,COALESCE(D.MPREQUESTUSERNAME,'-') AS create_by
	           ,COALESCE(E.CITY_ID,'-') AS city_id
						 ,COALESCE(f.CITY_NAME,'-') AS city_name
	           ,COALESCE(D.MPREQUESTUSERPHONE,'-') AS create_phone
			   ,A.SALES_SMS_CONTENT AS content
	FROM IOPDATA.IOP_MARKET_TASK A
	INNER JOIN IOPDATA.IOP_MARKET_CHANNEL B
	ON A.SVC_TYPE=B.SVC_TYPE
	LEFT JOIN IOPDATA.IOP_MARKET_POLICY C
	ON B.MPSID=C.MPSID
	LEFT JOIN (SELECT MPID, MPREQUESTUSERPHONE,MPREQUESTUSERNAME,MPCREATEUSERID AS USER_ID,MPNAME FROM IOPDATA.IOP_MARKET_PLAN) D
	ON C.MPID=D.MPID
	LEFT JOIN (SELECT USER_ID,CITY_ID FROM IOPDATA.IOP_SYS_DM_USER) E
	ON D.USER_ID=E.USER_ID
	left join IOPDATA.IOP_SYS_CITY f on E.city_id = f.city_id
	WHERE A.EFCT_DATE <=curdate() AND A.END_DATE > curdate()
    AND  B.svc_type = :code
