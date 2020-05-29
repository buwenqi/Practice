class Solution {

    //solution1, recursion
    public boolean isMatch(String s, String p) {
        if(s==null || p==null){
            return false;
        }
        char[] Schars=s.toCharArray();
        char[] Pchars=p.toCharArray();
        int sindex=0;
        int pindex=0;
        return isMatch(sindex,pindex,Schars,Pchars);
    }

    public boolean isMatch(int sindex,int pindex,char[] Schars,char[] pchars){
        if(sindex==Schars.length && pindex==pchars.length){
            return true;
        }else if(pindex==pchars.length){
            return false;
        }else{
            //recursive call
            if(pindex+1!=pchars.length && pchars[pindex+1]=='*'){
                //if the next pchar is *, we should take it differnt,
                if(sindex!=Schars.length &&(pchars[pindex]==Schars[sindex] || pchars[pindex]=='.')){
                    //1. zero match with *, skip * and current char
                    //2. current pchar match to current schar once, skip * char directly
                    //3. Repeating pchar with k times. It is equal to keep the pindex unchanged while sindex adds one
                    return isMatch(sindex,pindex+2,Schars,pchars)||
                        isMatch(sindex+1,pindex+2,Schars,pchars)||
                        isMatch(sindex+1,pindex,Schars,pchars);
                }else{
                    //when sindex==Schars.length, we should also skip the * 
                    //if pchars[pindex] is not equal to Schars[sindex], we can only skip the * matching.
                    return isMatch(sindex,pindex+2,Schars,pchars);
                }
            }else{
                if(sindex!=Schars.length && (pchars[pindex]==Schars[sindex]|| pchars[pindex]=='.')){
                    return isMatch(sindex+1,pindex+1,Schars,pchars);
                }else{
                    return false;
                }
            }
        }
    }

    //solution2: recursion optimize with DP array

    private int[][] dp;
    public boolean isMatch(String s, String p) {
        if(s==null || p==null){
            return false;
        }
        initDpArray(s.length(),p.length());
        char[] Schars=s.toCharArray();
        char[] Pchars=p.toCharArray();
        int sindex=0;
        int pindex=0;
        return isMatch(sindex,pindex,Schars,Pchars);
    }

    public void initDpArray(int Xlen,int Ylen){
        dp=new int[Xlen][Ylen];
        for(int i=0;i<Xlen;i++){
            for(int j=0;j<Ylen;j++){
                //-1 represents it is not being set
                //0 represents false and 1 represents true
                dp[i][j]=-1;
            }
        }
    }

    public boolean isMatch(int sindex,int pindex,char[] Schars,char[] pchars){
        boolean result=false;
        if(sindex!=Schars.length && pindex!=pchars.length && dp[sindex][pindex]!=-1){
            return dp[sindex][pindex]==1?true:false;
        }else if(sindex==Schars.length && pindex==pchars.length){
            result=true;
        }else if(pindex==pchars.length){
            result=false;
        }else{
            //recursive call
            if(pindex+1!=pchars.length && pchars[pindex+1]=='*'){
                //if the next pchar is *, we should take it differnt,
                if(sindex!=Schars.length &&(pchars[pindex]==Schars[sindex] || pchars[pindex]=='.')){
                    //1. zero match with *, skip * and current char
                    //2. current pchar match to current schar once, skip * char directly
                    //3. Repeating pchar with k times. It is equal to keep the pindex unchanged while sindex adds one
                    result=isMatch(sindex,pindex+2,Schars,pchars)||
                        isMatch(sindex+1,pindex+2,Schars,pchars)||
                        isMatch(sindex+1,pindex,Schars,pchars);
                    dp[sindex][pindex]=result?1:0;
                }else{
                    //when sindex==Schars.length, we should also skip the * 
                    //if pchars[pindex] is not equal to Schars[sindex], we can only skip the * matching.
                    result=isMatch(sindex,pindex+2,Schars,pchars);
                }
            }else{
                if(sindex!=Schars.length && (pchars[pindex]==Schars[sindex]|| pchars[pindex]=='.')){
                    result=isMatch(sindex+1,pindex+1,Schars,pchars);
                    dp[sindex][pindex]=result?1:0;
                }else{
                    result=false;
                }
            }
        }
        return result;
    }
}