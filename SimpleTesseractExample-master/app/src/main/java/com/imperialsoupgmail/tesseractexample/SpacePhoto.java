package com.imperialsoupgmail.tesseractexample;

import android.os.Parcel;
import android.os.Parcelable;

public class SpacePhoto implements Parcelable {

    private String mUrl;
    private String mTitle;
    SpacePhoto[] test ;

    public SpacePhoto(String url, String title) {
        mUrl = url;
        mTitle = title;
    }

    protected SpacePhoto(Parcel in) {
        mUrl = in.readString();
        mTitle = in.readString();
    }

    public static final Creator<SpacePhoto> CREATOR = new Creator<SpacePhoto>() {
        @Override
        public SpacePhoto createFromParcel(Parcel in) {
            return new SpacePhoto(in);
        }

        @Override
        public SpacePhoto[] newArray(int size) {
            return new SpacePhoto[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public static  SpacePhoto[] getSpacePhotos() {



        return new SpacePhoto[]{
                new SpacePhoto("http://i.imgur.com/CNmIkxo.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZoxVBd7.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/dofvmKv.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/S9kQaOx.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/4kStguh.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/pWrbElx.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/eTCFbha.jpg","Image"),
                new SpacePhoto("http://i.imgur.com/69SHrAr.jpg","Image"),
                new SpacePhoto("http://i.imgur.com/0xkpKTJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/tWPvyye.jpg","Image"),
                new SpacePhoto("http://i.imgur.com/psk1CDF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/BXivMQ6.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/5I6TJd8.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/xx2sQCG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/JsutWgT.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/arAHohR.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/e3cQ0gF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/s2Ky5tS.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/VHxyGu1.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/uLrj0I2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/knTnXBW.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/1BVGQDV.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/hsdRA67.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/7cnHet8.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/g7TYg3B.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/DuB4mjl.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/5JQpO9P.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/AfwqBJF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ckMazH6.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/uRLXryJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/9iuSIvA.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/tZA6Hwq.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/g2pOSxI.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/1l7xykB.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/KF3RIYU.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/lDH1kMF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/bRNl81K.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/0Dx6dAn.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/2Oc2Xkz.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/4yuMhqu.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/KlkH4vV.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/GJQEEe6.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/qtAYhJu.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/QA2OYbg.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/AzUR0iD.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/gc7JswM.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/TnD2kJz.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Lf3fJBx.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/eSX7SqY.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/mcM6SYO.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/eiq4TpQ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/py5AS1T.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/I9UUdUJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/i4BR3gk.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/xaF0Qmh.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/WOztBku.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/kGZTqF4.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/gn9g8mG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/2HI8Wz6.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/GqBPpqF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/edOAWXK.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/w3CIhMA.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/mLGUR7I.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/RZKMepm.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/BboO736.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/0LaKufy.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/FPws7bL.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Caa30ug.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/hePXul1.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/nfIvIbC.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ckPtdpG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/GVBtSqq.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/8XpsRLT.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/l6jtHlY.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/NfIpwvO.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/HZ5rmxd.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Gq3Scjp.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/s01KemR.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/amezjZ1.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/5Gs1vgJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/EhVWLPt.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/N2kKuUw.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/LwISN5i.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/VStRjQ3.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/TCv7xuG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/1sxq6tw.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/k5YXxME.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/e5PMtmg.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/CdmEVk2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/vU8odUf.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/AXE3eq7.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZmovPc6.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/gcf1NsL.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/vB2xJs5.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/pFnqDAT.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ygfZK65.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/kHSF3ja.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/2pmuRXm.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/cWoRvOp.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/PIb0ELi.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/PG5PFM2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/3qmQ4HB.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/IVhH4Sh.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/1T2Wk5W.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/QzIBF9k.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/45RnKJx.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ojlDoCW.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/5T38Npg.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/uj3b9ir.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/3Yz25Dz.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Y3ljNBJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/cW2qtrb.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZW0Zcr2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/pyd8LwM.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/r1miMZD.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/WCZIo0K.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/pbgSoSn.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/A4h5rqH.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/6Ei49lu.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/eY7UgHY.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/n2Qc77G.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/2PUZsiD.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/2GK4Q2S.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/iI0sDDo.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/y0gyKP7.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/oaTwLB4.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/WBBvrKW.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/GgJocjm.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/boGWITI.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/BZrjpDN.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/TJkSa7N.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/vxy21Id.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/AKJd77I.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/oiqWjmg.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/zlNQmZN.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/h3riwWn.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/bsXR3Bm.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/AbXxZSB.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/p74VX3c.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/1RiWq1f.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/PK24CJH.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/UxDrTxo.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/FFRPy7d.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZJkSyJt.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/BrixQbl.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/cHqwJLV.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/cLhjHoK.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/a9dcEjN.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/sj1uVmy.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/6psTXzw.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/MEhH0IX.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/mr5h5r8.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/vGMWBcp.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/iQx602x.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/II7QDP6.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Kzs3IZK.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Yp7FPyQ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ECMkowX.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/5K6tCdp.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/CExDBU0.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZDduCxn.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Bj8EiRJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/2mo48ug.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/AUbcG0E.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/qcnGZOv.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/O8T8s3O.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/5bth40G.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/HFsSfuV.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/og2PCVg.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/WT9uPJW.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/RC7ksFt.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/tB6qdvJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/6jKwFAV.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/JPz7hPK.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/N9itxKh.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZNpnyGY.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/kNLMy1h.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/PXgCmql.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/CTq77GM.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZQ6jVr7.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/8yVU3gk.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/rQK2Sxz.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/pFGoTpw.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/NXoLPq8.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Zl9GJSQ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/pWoqE6b.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/9dkjdTE.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/tblAsfN.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/lem0sIG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/rWiqJLs.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/VwUsr1k.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/qyx3Xje.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/JGQ0OVv.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/pIgHAg0.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/wzFa2s5.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/V8HcN7M.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/9LUVaYk.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/qrUYqb9.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/xZD143U.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/muML4KW.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/t1V1oqU.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/rtXMiKm.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/gr62RtF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/52jEDOF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/1197VTX.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/xeBxDi2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/QKWDjW9.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ovyEAiv.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/jwZt4Hf.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/qPGRnmX.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/q6baijl.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/RRhsdgB.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/CwRV5cp.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZUpKo9w.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/wWXwWYK.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/LbcAWAn.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ZIZ6wZv.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/XHGWPDV.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/YviCEUU.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/twTvbiH.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/hfNAYYG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/e70Bd85.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/EBBbUmo.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/WnT0DI2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/dYfNM0d.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Z0LIe63.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/d0J0fwF.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/uhU2Nf1.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/wxSfKhr.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/fzex6X7.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/cpl4vNC.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/McGbgGo.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/crwRSTi.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/mITpwCr.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/EbeqexH.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/OHMgHsu.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/zxtJcP2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/zxtJcP2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/mDs3koS.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/LH2Lbk3.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/5lSVTPD.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/YMz8Yti.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/UL06GHG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/y2OpypN.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/kJNWMnd.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/3LAWTQK.png", "Image"),
                new SpacePhoto("http://i.imgur.com/PDXMDGv.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/OKgGxXl.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/c9qn4dX.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/M9Qm38P.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/YikMFpm.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/x4EEb5p.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/7JaxVVg.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/lCN1QyD.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/yJxmKht.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Ls2dItj.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/vCF9yvJ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Beav8FZ.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ihbZ7mf.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/VpZv96Q.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/kfn9oln.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/25i6lzx.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/zcbswNn.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/GjVXw95.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/SCmsenX.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/idYnpXR.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/uZqE99f.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/eJzmqUU.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/xdtXG10.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/9ygGTjf.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/f9eTZpT.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/BHtRfkT.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/8CTrSaV.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/YcacEI1.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/JYSIeDG.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/KVIp51u.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/JuKiuEs.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/Wely8vk.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/tMXCpFq.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/fd0TnI4.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ewr40ti.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/rZCZ5H2.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/JRAYqQI.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/EtnqK9d.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/0KZJ40n.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/ayvT51x.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/xKgk5A5.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/9cE5dFg.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/PE9LQOX.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/JJYxfir.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/uCVIN6o.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/aHaTL3n.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/IoOxZnA.jpg", "Image"),
                new SpacePhoto("http://i.imgur.com/bMCmzKK.jpg", "Image"),
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mTitle);
    }
}
