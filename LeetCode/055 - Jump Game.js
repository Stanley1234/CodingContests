var canJump = function(nums) {
    
    var farthest = 0;
    for(var i = 0;i < nums.length - 1;i ++) {
        
        if(farthest <= i && nums[i] == 0)
            return false;
        farthest = Math.max(farthest, i + nums[i]);
    }
    return true;
};
