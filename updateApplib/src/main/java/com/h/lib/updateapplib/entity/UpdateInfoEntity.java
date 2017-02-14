/*
 * Copyright 2016 czy1121
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.h.lib.updateapplib.entity;

public class UpdateInfoEntity {
    // 是否有新版本
    public boolean hasUpdate = false;
    // 是否静默下载：有新版本时不提示直接下载，下次启动时安装
    public boolean isSilent = false;
    // 是否强制安装：不安装无法使用app
    public boolean isForce = false;
    // 是否可忽略该版本
    public boolean isIgnorable = true;
    // 是否是增量补丁包
    public boolean isPatch = false;

    public int versionCode;
    public String versionName;

    public String updateContent;

    public String url;
    public String md5;
    public long size;

    public String patchUrl;
    public String patchMd5;
    public long patchSize;



}